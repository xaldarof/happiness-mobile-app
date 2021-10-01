package pdf.reader.happiness.presentation.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import nl.dionsegijn.konfetti.KonfettiView
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import pdf.reader.happiness.R
import pdf.reader.happiness.data.core.DataRepository
import pdf.reader.happiness.data.settings_cache.CongratulationController
import pdf.reader.happiness.databinding.FragmentMainBinding
import pdf.reader.happiness.presentation.MainFragmentPresenter
import pdf.reader.happiness.tools.CongratulationView

@KoinApiExtension
class MainFragment : Fragment(), KoinComponent, MainFragmentPresenter.MyView {

    private lateinit var binding: FragmentMainBinding
    private val repository: DataRepository by inject()
    private val mainFragmentPresenter = MainFragmentPresenter(this)
    private lateinit var konfettiView: KonfettiView
    private val congratulation:CongratulationController by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        konfettiView = requireActivity().findViewById(R.id.congratulationView)
        binding.successFinishedIcon.visibility = View.INVISIBLE
        binding.lifeFinishedIcon.visibility = View.INVISIBLE
        binding.loveFinishedIcon.visibility = View.INVISIBLE
        binding.happyFinishedIcon.visibility = View.INVISIBLE


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeProgress()

        binding.successBtn.setOnClickListener {
            navigate(SuccessFragment())
        }
        binding.lifeBtn.setOnClickListener {
            navigate(LifeFragment())
        }
        binding.happyBtn.setOnClickListener {
            navigate(HappinessFragment())
        }

        binding.loveBtn.setOnClickListener {
            navigate(LoveFragment())
        }
    }

    private fun navigate(fragment: Fragment) {
        parentFragmentManager.beginTransaction().replace(R.id.containerMain, fragment)
            .addToBackStack(null).commit()
    }

    override fun onResume() {
        super.onResume()
        observeProgress()
    }

    private fun observeProgress() {
        CoroutineScope(Dispatchers.Main).launch {
            observeSuccess()
            observeLife()
            observeLove()
            observeHappy()
            observeCorePercent()
        }
    }

    private suspend fun observeCorePercent() {
        repository.fetchAllTypes().asLiveData().observeForever {
            mainFragmentPresenter.updatePercentCore(it)
            mainFragmentPresenter.updateAllFinished(it)
        }
    }

    private suspend fun observeSuccess() {
        repository.fetchSuccess().asLiveData().observeForever {
            binding.successCountTv.text = "${it.size} советов"
            mainFragmentPresenter.updatePercentSuccess(it)
            mainFragmentPresenter.updateAllSuccessFinished(it)
        }
    }

    private suspend fun observeLife() {
        repository.fetchLife().asLiveData().observeForever {
            binding.lifeCounterTv.text = "${it.size} советов"
            mainFragmentPresenter.updatePercentLife(it)
            mainFragmentPresenter.updateAllLifeFinished(it)
        }
    }

    private suspend fun observeHappy() {
        repository.fetchHappy().asLiveData().observeForever {
            binding.happyCounterTv.text = "${it.size} советов"
            mainFragmentPresenter.updatePercentHappy(it)
            mainFragmentPresenter.updateAllHappyFinished(it)
        }
    }

    private suspend fun observeLove() {
        repository.fetchLove().asLiveData().observeForever {
            binding.loveCountTv.text = "${it.size} советов"
            mainFragmentPresenter.updatePercentLove(it)
            mainFragmentPresenter.updateAllLoveFinished(it)
        }
    }

    override fun updateLifePercent(percent: Float) {
        binding.progressViewLife.setEndProgress(percent)
        binding.progressViewLife.startProgressAnimation()
    }

    override fun updateSuccessPercent(percent: Float) {
        binding.progressViewSuccess.setEndProgress(percent)
        binding.progressViewSuccess.startProgressAnimation()
    }

    override fun updateHappyPercent(percent: Float) {
        binding.progressViewHappy.setEndProgress(percent)
        binding.progressViewHappy.startProgressAnimation()
    }

    override fun updateLovePercent(percent: Float) {
        binding.progressViewLove.setEndProgress(percent)
        binding.progressViewLove.startProgressAnimation()
    }


    override fun updateCorePercent(percent: Float) {
        binding.progressCore.setEndProgress(percent)
        binding.progressCore.startProgressAnimation()
    }

    override fun updateAllLifeFinished() {
        binding.lifeFinishedIcon.visibility = View.VISIBLE
        if (!congratulation.isLifeCongratulated()){
            CongratulationView(konfettiView).show()
            congratulation.setLiveCongratulated(true)
        }
    }

    override fun updateAllSuccessFinished() {
        binding.successFinishedIcon.visibility = View.VISIBLE
        if (!congratulation.isSuccessCongratulated()) {
            CongratulationView(konfettiView).show()
            congratulation.setSuccessCongratulated(true)
        }
    }

    override fun updateAllHappyFinished() {
        binding.happyFinishedIcon.visibility = View.VISIBLE
        if (!congratulation.isHappyCongratulated()){
            CongratulationView(konfettiView).show()
            congratulation.setHappyCongratulated(true)
        }
    }

    override fun updateAllLoveFinished() {
        binding.loveFinishedIcon.visibility = View.VISIBLE
        if (!congratulation.isLoveCongratulated()){
            CongratulationView(konfettiView).show()
            congratulation.setLoveCongratulated(true)
        }
    }

    override fun updateAllFinished() {
        if (!congratulation.isAllCongratulated()){
            CongratulationView(konfettiView).show()
            congratulation.setAllCongratulated(true)
        }
    }
}