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
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import pdf.reader.happiness.core.Status
import pdf.reader.happiness.databinding.FragmentTokensBinding
import pdf.reader.happiness.tools.ConnectionManager
import pdf.reader.happiness.tools.TokenDialog
import pdf.reader.happiness.vm.TokenViewModel


@KoinApiExtension
class TokensFragment : Fragment(), KoinComponent,TokenDialog.CallBack {

    private lateinit var binding: FragmentTokensBinding
    private val viewModel: TokenViewModel = get()
    private var coinCount = 0

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

        CoroutineScope(Dispatchers.IO).launch {
            viewModel.createToken(23)
        }
        binding.createTokenBtn.setOnClickListener {
            if (connectionManager.isConnected()) {
                val tokenId = binding.idEditText.text.toString()
                CoroutineScope(Dispatchers.IO).launch {
                    val result = viewModel.fetchTokenById(tokenId)

                   withContext(Dispatchers.Main) {

                       when (result.status) {
                           Status.SUCCESS -> {
                               TokenDialog.Base(requireContext())
                                   .show(result.data!!.mapToTokenModel(), this@TokensFragment)
                               coinCount = result.data.tokenValue
                           }

                           Status.ERROR -> {
                               Toast.makeText(requireContext(), "Данный токен не существует.", Toast.LENGTH_SHORT)
                                   .show()
                           }
                       }


                   }
                }
            }else {
                Toast.makeText(requireContext(), "Ошибка подключения...", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    override fun onClickActive() {
        viewModel.updateUserCoinCount(coinCount)
    }
}