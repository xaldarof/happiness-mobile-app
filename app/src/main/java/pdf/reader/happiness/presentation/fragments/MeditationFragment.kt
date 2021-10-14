package pdf.reader.happiness.presentation.fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import pdf.reader.happiness.R
import pdf.reader.happiness.databinding.FragmentMeditationBinding
import pdf.reader.happiness.vm.MeditationFragmentViewModel
import pdf.reader.happiness.tools.hideUi
import pdf.reader.happiness.tools.showUi
import pdf.reader.happiness.tools.vibrate


@KoinApiExtension
class MeditationFragment : Fragment(), KoinComponent,MeditationFragmentViewModel.CallBack {

    private lateinit var binding: FragmentMeditationBinding
    private val viewModel: MeditationFragmentViewModel = get()
    private var isOpened = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMeditationBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Toast.makeText(requireContext(), R.string.connect_headphones, Toast.LENGTH_LONG).show()

        if (!isOpened){
            requireActivity().hideUi()
            isOpened = true
        }

        binding.containerMain.setOnClickListener {
            if (isOpened) requireActivity().hideUi()
        }

        CoroutineScope(Dispatchers.Main).launch {
            viewModel.startMeditation(this@MeditationFragment)
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
        requireActivity().showUi()
        viewModel.stopMusic()
    }

    override fun onFinish() {
        CoroutineScope(Dispatchers.Main).launch {
            Toast.makeText(requireContext(), R.string.have_good_day, Toast.LENGTH_LONG).show()
            requireActivity().supportFragmentManager.popBackStack()
            requireContext().vibrate()
        }
    }
}