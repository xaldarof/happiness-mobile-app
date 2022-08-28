package pdf.reader.happiness.presentation.fragments

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import pdf.reader.happiness.R
import pdf.reader.happiness.databinding.FragmentExchangeBinding
import pdf.reader.happiness.presentation.activity.ExchangeCoinRoomActivity
import pdf.reader.happiness.tools.*
import pdf.reader.happiness.vm.ImportingActivityViewModel


@KoinApiExtension
class ExchangeFragment : Fragment(), KoinComponent, ImportingActivityViewModel.CallBack {

    private lateinit var binding: FragmentExchangeBinding
    private val viewModel: ImportingActivityViewModel = get()
    private var userOn = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentExchangeBinding.inflate(inflater, container, false)
        userOn = true
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.progressView.visibility = View.INVISIBLE

        val viewPager2 = requireActivity().findViewById<ViewPager2>(R.id.pagerImportActivity)
        binding.share.setOnClickListener { viewPager2.currentItem++ }

        binding.info.setOnClickListener {
            ImportInfoDialog.Base().show(requireContext())
        }

        binding.exchangeRoom.setOnClickListener {
            startActivity(Intent(requireContext(), ExchangeCoinRoomActivity::class.java))
        }

        binding.start.setOnClickListener {
            val commonPrice = binding.priceTv.text.toString().toInt()

            if (viewModel.fetchUserCoinCount() >= commonPrice && binding.editTextCount.text.toString()
                    .isNotEmpty()
            ) {
                CoroutineScope(Dispatchers.Main).launch {
                    importData(binding.editTextCount.text.toString().toInt())
                }

            } else {
                if (binding.editTextCount.text.toString().isEmpty()) {
                    binding.editTextCount.errorAnimation()
                    Toast.makeText(
                        requireContext(),
                        "Введите количетсво статьей !",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    binding.balanceLayout.errorAnimation()
                    Toast.makeText(
                        requireContext(),
                        "У вас не хватает количество монет",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        binding.editTextCount.addTextChangedListener(MyTextWatcher(object : CallBack {
            override fun onChange(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.priceTv.text = if (binding.editTextCount.text.toString().isNotEmpty())
                    (binding.editTextCount.text.toString().toLong() * 3).toString() else "0"
            }
        }))
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchUserCoinAsLiveData().observe(viewLifecycleOwner) {
            binding.balanceTv.text = it.toString()
        }
    }

    private suspend fun importData(userEnteredInfoCount: Int) {
        val connectionManager = ConnectionManager(requireContext())

        if (connectionManager.isConnected()) {
            CoroutineScope(Dispatchers.Main).launch {
                binding.progressView.visibility = View.VISIBLE
                binding.start.isEnabled = false
            }
            viewModel.startImporting(this, userEnteredInfoCount)
        } else {
            CoroutineScope(Dispatchers.Main).launch {
                Toast.makeText(
                    requireContext(), R.string.no_connection,
                    Toast.LENGTH_SHORT
                ).show()
                connectionManager.enableWifi()
            }
        }
    }

    private fun onFinishExchange() {
        binding.progressView.visibility = View.INVISIBLE
        binding.start.isEnabled = true
    }

    override fun onSuccessCallBack(count: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.payWithCoin(binding.priceTv.text.toString().toInt(), onSuccess = {
                onFinishExchange()
                if (userOn) {
                    Toast.makeText(
                        requireContext(), "Обмен успешно завершен. Вы получили $count новых данных",
                        Toast.LENGTH_SHORT
                    ).show()
                    requireActivity().finish()
                }
            }, onFail = {
                Toast.makeText(
                    requireContext(), it,
                    Toast.LENGTH_SHORT
                ).show()
            })
        }
    }

    override fun onOutOfBounds(count: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            if (userOn) {
                Toast.makeText(
                    requireContext(),
                    "Вы просите слишком большое количество данных, максимум: $count",
                    Toast.LENGTH_SHORT
                ).show()
            }
            onFinishExchange()
        }
    }

    override fun onPause() {
        super.onPause()
        userOn = false
    }
}