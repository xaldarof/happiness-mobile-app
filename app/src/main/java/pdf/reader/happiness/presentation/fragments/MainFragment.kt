package pdf.reader.happiness.presentation.fragments

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.asLiveData
import androidx.navigation.Navigation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.component.inject
import pdf.reader.happiness.R
import pdf.reader.happiness.data.core.DataRepository
import pdf.reader.happiness.data.models.InfoModel
import pdf.reader.happiness.databinding.FragmentMainBinding
import pdf.reader.happiness.presentation.MainFragmentPresenter
import pdf.reader.happiness.presentation.adapter.WordsAdapter
import pdf.reader.happiness.vm.MainFragmentViewModel

@KoinApiExtension
class MainFragment : Fragment(),KoinComponent,MainFragmentPresenter.MyView {

    private lateinit var binding : FragmentMainBinding
    private val repository:DataRepository by inject()
    private val mainFragmentPresenter = MainFragmentPresenter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        val wordsAdapter = WordsAdapter(listOf("sas","sas","sa"), layoutInflater, requireContext())
//        binding.viewPager.adapter = wordsAdapter
//        binding.dotsIndicator.setViewPager(binding.viewPager)
        binding.progressViewCircle.setStartProgress(0F)
        binding.progressViewCircle.setEndProgress(90F)
        binding.progressViewCircle.setProgressDuration(2000)
        binding.progressViewCircle.startProgressAnimation()

        CoroutineScope(Dispatchers.Main).launch {
            updateWords()
        }

        return binding.root
    }

    private suspend fun updateWords(){
//        while (true) {
//            binding.viewPager.currentItem = (0..3).random()
//            delay(5000)
//        }
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
        parentFragmentManager.beginTransaction().replace(R.id.containerMain,fragment).addToBackStack("back").commit()
    }

    override fun onResume() {
        super.onResume()
        observeProgress()
    }

    private fun observeProgress(){
        CoroutineScope(Dispatchers.Main).launch {
            observeSuccess()
            observeLife()
            observeLove()
            observeHappy()
        }
    }

    private suspend fun observeSuccess() {
        repository.fetchSuccess().asLiveData().observeForever {
            binding.successCountTv.text = "${it.size} советов"
            mainFragmentPresenter.updatePercentSuccess(it)
        }
    }
    private suspend fun observeLife(){
        repository.fetchLife().asLiveData().observeForever {
            binding.lifeCounterTv.text = "${it.size} советов"
            mainFragmentPresenter.updatePercentLife(it)
        }
    }
    private suspend fun observeHappy(){
        repository.fetchHappy().asLiveData().observeForever {
            binding.happyCounterTv.text = "${it.size} советов"
            mainFragmentPresenter.updatePercentHappy(it)
        }
    }

    private suspend fun observeLove(){
        repository.fetchLove().asLiveData().observeForever {
            binding.loveCountTv.text = "${it.size} советов"
            mainFragmentPresenter.updatePercentLove(it)
        }
    }


    override fun updateLifePercent(percent: Double) {
        binding.progressViewLife.setProgressDuration(2000)
        binding.progressViewLife.setEndProgress(percent.toFloat())
        binding.progressViewLife.startProgressAnimation()
    }

    override fun updateSuccessPercent(percent: Double) {
        binding.progressViewSuccess.setProgressDuration(2000)
        binding.progressViewSuccess.setEndProgress(percent.toFloat())
        binding.progressViewSuccess.startProgressAnimation()    }

    override fun updateHappyPercent(percent: Double) {
        binding.progressViewHappy.setProgressDuration(2000)
        binding.progressViewHappy.setEndProgress(percent.toFloat())
        binding.progressViewHappy.startProgressAnimation()    }

    override fun updateLovePercent(percent: Double) {
        binding.progressViewLove.setProgressDuration(2000)
        binding.progressViewLove.setEndProgress(percent.toFloat())
        binding.progressViewLove.startProgressAnimation()    }
}