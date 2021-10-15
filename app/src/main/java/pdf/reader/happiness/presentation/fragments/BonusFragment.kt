package pdf.reader.happiness.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.gms.ads.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import pdf.reader.happiness.databinding.FragmentBonusBinding
import pdf.reader.happiness.tools.RewardedAdManager
import pdf.reader.happiness.vm.BonusFragmentViewModel
import pdf.reader.happiness.R

@KoinApiExtension
class BonusFragment : Fragment(), RewardedAdManager.CallBack,KoinComponent {

    private lateinit var binding: FragmentBonusBinding
    private val viewModel: BonusFragmentViewModel = get()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentBonusBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.shimmer.startShimmer()
        binding.progress.visibility = View.INVISIBLE
        binding.backBtn.setOnClickListener { requireActivity().supportFragmentManager.popBackStack() }

        viewModel.fetchUserCoinCountAsFlow().observe(viewLifecycleOwner) {
            binding.countTv.text = it.toString()
        }


        binding.startBtn.setOnClickListener {
            onStartAd()
            CoroutineScope(Dispatchers.Main).launch {
                RewardedAdManager(this@BonusFragment).showRewardedAd(requireContext())
            }
            binding.startBtn.setImageResource(R.drawable.ic_baseline_pause_circle_outline_24)
        }

        CoroutineScope(Dispatchers.IO).launch {
            MobileAds.initialize(requireContext())
        }

    }

    private fun onStartAd(){
        binding.progress.visibility = View.VISIBLE
        binding.startBtn.isEnabled = false
        binding.shimmer.stopShimmer()
    }

    private fun onAdFinish() {
        binding.startBtn.setImageResource(R.drawable.ic_baseline_play_circle_outline_24)
        binding.progress.visibility = View.INVISIBLE
        binding.startBtn.isEnabled = true
        binding.shimmer.startShimmer()

    }

    override fun onSuccessReward() {
        viewModel.updateUserCoinCount()
    }

    override fun onNetworkError() {
        Toast.makeText(requireActivity().applicationContext, "Произошла ошибка соединения", Toast.LENGTH_SHORT).show()
        onAdFinish()
    }

    override fun handledAnError() {
        Toast.makeText(requireContext().applicationContext, "Произошла ошибка при показе рекламы", Toast.LENGTH_SHORT).show()
        onAdFinish()
    }

    override fun onDismiss() {
        onAdFinish()
    }

    override fun onAdIsNotReady() {
        Toast.makeText(requireContext().applicationContext, "Реклама еще не готова к показу", Toast.LENGTH_SHORT).show()
        onAdFinish()
    }
}