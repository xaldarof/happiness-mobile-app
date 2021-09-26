package pdf.reader.happiness.presentation.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.asLiveData
import androidx.navigation.ActivityNavigator
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import pdf.reader.happiness.R
import pdf.reader.happiness.data.core.DataRepository
import pdf.reader.happiness.databinding.FragmentMainBinding
import java.io.BufferedReader
import java.io.InputStreamReader

class MainFragment : Fragment(),KoinComponent {

    private lateinit var binding : FragmentMainBinding
    private val repository:DataRepository by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.successBtn.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_mainFragment_to_successFragment)
        }
        binding.lifeBtn.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_mainFragment_to_lifeFragment)
        }
        CoroutineScope(Dispatchers.Main).launch {
            successSize()
            lifeSize()
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
}