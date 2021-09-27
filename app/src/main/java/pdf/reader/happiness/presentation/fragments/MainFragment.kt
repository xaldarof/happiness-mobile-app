package pdf.reader.happiness.presentation.fragments

import android.os.Bundle
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
import org.koin.core.component.inject
import pdf.reader.happiness.R
import pdf.reader.happiness.data.core.DataRepository
import pdf.reader.happiness.databinding.FragmentMainBinding
import pdf.reader.happiness.presentation.adapter.WordsAdapter

@KoinApiExtension
class MainFragment : Fragment(),KoinComponent {

    private lateinit var binding : FragmentMainBinding
    private val repository:DataRepository by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        val wordsAdapter = WordsAdapter(
            listOf(
                "Жизнь состоит не в том, чтобы найти себя. Жизнь состоит в том, чтобы создать себя.",
                "sasasasqe",
                "1111"
            ), layoutInflater, requireContext()
        )
        binding.viewPager.adapter = wordsAdapter
        binding.dotsIndicator.setViewPager(binding.viewPager)

        CoroutineScope(Dispatchers.Main).launch {
            updateWords()
        }

        return binding.root
    }

    private suspend fun updateWords(){
        while (true) {
            binding.viewPager.currentItem = (0..3).random()
            delay(5000)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.successBtn.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_mainFragment_to_successFragment)
        }
        binding.lifeBtn.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_mainFragment_to_lifeFragment)
        }
        binding.happyBtn.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_mainFragment_to_happinessFragment)
        }

        binding.loveBtn.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_mainFragment_to_loveeFragment)
        }

        CoroutineScope(Dispatchers.Main).launch {
            successSize()
            lifeSize()
            happySize()
            loveSize()
        }
    }
    private suspend fun successSize(){
        repository.fetchSuccess().asLiveData().observeForever {
            binding.successCountTv.text = "${it.size} советов"
        }
    }
    private suspend fun lifeSize(){
        repository.fetchLife().asLiveData().observeForever {
            binding.lifeCounterTv.text = "${it.size} советов"
        }
    }
    private suspend fun happySize(){
        repository.fetchHappy().asLiveData().observeForever {
            binding.happyCounterTv.text = "${it.size} советов"
        }
    }

    private suspend fun loveSize(){
        repository.fetchLove().asLiveData().observeForever {
            binding.loveCountTv.text = "${it.size} советов"
        }
    }
}