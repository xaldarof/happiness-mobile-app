package pdf.reader.happiness.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import pdf.reader.happiness.databinding.FragmentMeditationBinding
import pdf.reader.happiness.vm.MeditationFragmentViewModel

@KoinApiExtension
class MeditationFragment : Fragment(), KoinComponent {

    private lateinit var binding: FragmentMeditationBinding
    private val viewModel: MeditationFragmentViewModel = get()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMeditationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        CoroutineScope(Dispatchers.Main).launch {
            viewModel.startMeditation()
        }

        CoroutineScope(Dispatchers.Main).launch {
            viewModel.getMinute().observeForever {
                binding.minuteTv.text = it
            }

            viewModel.getSecond().observeForever {
                binding.secondTv.text = it
            }
        }
    }

    override fun onPause() {
        super.onPause()
        viewModel.stopMusic()
    }
}