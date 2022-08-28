package pdf.reader.happiness.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.ads.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import pdf.reader.happiness.databinding.FragmentBonusBinding
import pdf.reader.happiness.tools.RewardedAdManager
import pdf.reader.happiness.vm.BonusFragmentViewModel
import pdf.reader.happiness.R

@KoinApiExtension
class EarnFragment : Fragment(), RewardedAdManager.CallBack, KoinComponent {

    private lateinit var binding: FragmentBonusBinding
    private val viewModel: BonusFragmentViewModel = get()
    private var isUserOn = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBonusBinding.inflate(inflater, container, false)
        isUserOn = true
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rewardedAdManager = RewardedAdManager(this, requireContext())

        binding.progress.visibility = View.INVISIBLE
        binding.backBtn.setOnClickListener { requireActivity().supportFragmentManager.popBackStack() }

        lifecycleScope.launch {
            viewModel.fetchUserCoinCountAsFlow().collectLatest {
                binding.countTv.text = it.toString()
            }
        }


        binding.startBtn.setOnClickListener {
            onStartAd()
            CoroutineScope(Dispatchers.Main).launch {
                rewardedAdManager.showRewardedAd()
            }
            binding.startBtn.setImageResource(R.drawable.ic_baseline_pause_circle_outline_24)
        }

        CoroutineScope(Dispatchers.IO).launch {
            MobileAds.initialize(requireContext())
        }

    }

    override fun onStop() {
        super.onStop()
        isUserOn = false
    }

    private fun onStartAd() {
        binding.progress.visibility = View.VISIBLE
        binding.startBtn.isEnabled = false
        binding.shimmer.stopShimmer()
    }

    private fun onAdFinish(message: Int) {
        binding.startBtn.setImageResource(R.drawable.ic_baseline_play_circle_outline_24)
        binding.progress.visibility = View.INVISIBLE
        binding.startBtn.isEnabled = true
        binding.shimmer.startShimmer()
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT)
            .show()
    }

    override fun onSuccessReward() {
        viewModel.updateUserCoinCount(
            onSuccess = {
                binding.countTv.text = viewModel.fetchUserCoinCount().toString()
                Toast.makeText(requireContext(), "Успешно выплачено !", Toast.LENGTH_SHORT).show()
            }, onFail = {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            })
    }


    override fun onNetworkError() {
        if (isUserOn) {
            onAdFinish(R.string.error_network)
        }
    }

    override fun handledAnError() {
        if (isUserOn) {
            onAdFinish(R.string.handle_error)
        }
    }

    override fun onDismiss() {
        if (isUserOn) {
            onAdFinish(R.string.success_reward)
        }
    }

    override fun onAdIsNotReady() {
        if (isUserOn) {
            onAdFinish(R.string.ad_not_ready)
        }
    }
}