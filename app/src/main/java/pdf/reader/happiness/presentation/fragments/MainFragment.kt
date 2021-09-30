package pdf.reader.happiness.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import pdf.reader.happiness.R
import pdf.reader.happiness.data.core.DataRepository
import pdf.reader.happiness.databinding.FragmentMainBinding
import pdf.reader.happiness.presentation.MainFragmentPresenter
import pdf.reader.happiness.presentation.adapter.WordsAdapter

@KoinApiExtension
class MainFragment : Fragment(), KoinComponent, MainFragmentPresenter.MyView {

    private lateinit var binding: FragmentMainBinding
    private val repository: DataRepository by inject()
    private val mainFragmentPresenter = MainFragmentPresenter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        val wordsAdapter =
            WordsAdapter(listOf("sas", "sas", "sa"), layoutInflater, requireContext())
//        binding.viewPager.adapter = wordsAdapter
//        binding.dotsIndicator.setViewPager(binding.viewPager)

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
            .addToBackStack("back").commit()
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
        }
    }

    private suspend fun observeSuccess() {
        repository.fetchSuccess().asLiveData().observeForever {
            binding.successCountTv.text = "${it.size} советов"
            mainFragmentPresenter.updatePercentSuccess(it)
        }
    }

    private suspend fun observeLife() {
        repository.fetchLife().asLiveData().observeForever {
            binding.lifeCounterTv.text = "${it.size} советов"
            mainFragmentPresenter.updatePercentLife(it)
        }
    }

    private suspend fun observeHappy() {
        repository.fetchHappy().asLiveData().observeForever {
            binding.happyCounterTv.text = "${it.size} советов"
            mainFragmentPresenter.updatePercentHappy(it)
        }
    }

    private suspend fun observeLove() {
        repository.fetchLove().asLiveData().observeForever {
            binding.loveCountTv.text = "${it.size} советов"
            mainFragmentPresenter.updatePercentLove(it)
        }
    }


    override fun updateLifePercent(percent: Float) {
        binding.progressViewLife.setProgressDuration(2000)
        binding.progressViewLife.setEndProgress(percent)
        binding.progressViewLife.startProgressAnimation()
    }

    override fun updateSuccessPercent(percent: Float) {
        binding.progressViewSuccess.setProgressDuration(2000)
        binding.progressViewSuccess.setEndProgress(percent)
        binding.progressViewSuccess.startProgressAnimation()
    }

    override fun updateHappyPercent(percent: Float) {
        binding.progressViewHappy.setProgressDuration(2000)
        binding.progressViewHappy.setEndProgress(percent)
        binding.progressViewHappy.startProgressAnimation()
    }

    override fun updateLovePercent(percent: Float) {
        binding.progressViewLove.setProgressDuration(2000)
        binding.progressViewLove.setEndProgress(percent)
        binding.progressViewLove.startProgressAnimation()
    }

    override fun updateCorePercent(percent: Float) {
        binding.progressCore.setEndProgress(percent)
        binding.progressCore.startProgressAnimation()
    }
}