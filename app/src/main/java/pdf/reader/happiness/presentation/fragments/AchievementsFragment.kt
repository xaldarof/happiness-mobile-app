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
import pdf.reader.happiness.R
import pdf.reader.happiness.core.AchievementModel
import pdf.reader.happiness.databinding.FragmentAchievementsBinding
import pdf.reader.happiness.presentation.adapter.AchievementAdapter
import pdf.reader.happiness.tools.CustomSnackBar
import pdf.reader.happiness.vm.AchievementsViewModel
import java.util.*


@KoinApiExtension
class AchievementsFragment : Fragment(), KoinComponent {

    private lateinit var binding: FragmentAchievementsBinding
    private val viewModel: AchievementsViewModel = get()
    private lateinit var adapter: AchievementAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAchievementsBinding.inflate(inflater, container, false)
        adapter = AchievementAdapter()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rv.adapter = adapter
        CoroutineScope(Dispatchers.Main).launch {
            update()
        }

    }

    override fun onResume() {
        super.onResume()
        CoroutineScope(Dispatchers.Main).launch {
            update()
        }
    }

    private suspend fun update() {
        viewModel.fetchAchievements().observeForever {
            adapter.update(it)
            binding.achievementsCount.text = it.size.toString()
        }
    }
}