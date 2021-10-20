package pdf.reader.happiness.presentation.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import pdf.reader.happiness.R
import pdf.reader.happiness.core.CloudResult
import pdf.reader.happiness.core.TokenModel
import pdf.reader.happiness.databinding.FragmentTokensBinding
import pdf.reader.happiness.presentation.adapter.TokenHistoryItemAdapter
import pdf.reader.happiness.tools.ConnectionManager
import pdf.reader.happiness.tools.TokenDialog
import pdf.reader.happiness.tools.errorAnimation
import pdf.reader.happiness.vm.TokenViewModel


@KoinApiExtension
class TokensFragment : Fragment(), KoinComponent, TokenDialog.CallBack,
    TokenHistoryItemAdapter.OnClick {

    private lateinit var binding: FragmentTokensBinding
    private val viewModel: TokenViewModel = get()
    private var coinCount = 0
    private var tokenIdForRemove = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTokensBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val connectionManager = ConnectionManager(requireContext())
        OverScrollDecoratorHelper.setUpOverScroll(binding.scroll)
        val adapter = TokenHistoryItemAdapter(this)
        binding.rv.adapter = adapter
        binding.rv.isNestedScrollingEnabled = false

        CoroutineScope(Dispatchers.Main).launch {
            viewModel.fetchTokenHistory().observeForever {
                adapter.update(it)
            }
        }

        binding.infoBtn.setOnClickListener { TokenDialog.Base(requireContext()).showInfo() }
        binding.backBtn.setOnClickListener { requireActivity().supportFragmentManager.popBackStack() }

        binding.createTokenBtn.setOnClickListener {
            TokenDialog.Base(requireContext())
                .showCreateTokenDialog(viewModel.fetchUserCoinCount(), this)
        }

        binding.checkTokenBtn.setOnClickListener {
            val tokenId = binding.idEditText.text.toString()

            if (connectionManager.isConnected() && tokenId.isNotEmpty()) {

                CoroutineScope(Dispatchers.IO).launch {

                    val result = viewModel.fetchTokenById(tokenId)

                    withContext(Dispatchers.Main) {

                        when (result) {
                            is CloudResult.Success -> {
                                TokenDialog.Base(requireContext())
                                    .show(result.data.mapToTokenModel(), this@TokensFragment)

                                coinCount = result.data.tokenValue
                                tokenIdForRemove = result.data.tokenId
                            }

                            is CloudResult.Fail -> {
                                Toast.makeText(requireContext(), R.string.token_not_found, Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                    }
                }
            } else {

                if (tokenId.isEmpty()) binding.idEditText.errorAnimation()
                else Toast.makeText(requireContext(), R.string.no_connection, Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    override fun onClickActive() {
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.removeToken(tokenIdForRemove)
            viewModel.updateUserCoinCount(coinCount)
        }
    }

    override fun onClickCreate(id: String, userEnteredCount: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.createTokenByUser(userEnteredCount, id)
            viewModel.payWithCoin(userEnteredCount)
            viewModel.addTokenHistory(TokenModel(userEnteredCount,(System.currentTimeMillis()/1000).toString(),id))
        }
    }

    override fun onClick(tokenModel: TokenModel) {
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.deleteTokenHistory(tokenModel)
        }
    }
}