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
import org.jetbrains.annotations.NonNls
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.component.inject
import pdf.reader.happiness.R
import pdf.reader.happiness.core.ChapterModel
import pdf.reader.happiness.data.core.AchievementRepository
import pdf.reader.happiness.data.core.DataRepository
import pdf.reader.happiness.data.core.ToolsRepository
import pdf.reader.happiness.data.settings_cache.BadgeController
import pdf.reader.happiness.data.settings_cache.CongratulationController
import pdf.reader.happiness.databinding.FragmentMainBinding
import pdf.reader.happiness.presentation.MainFragmentPresenter
import pdf.reader.happiness.presentation.adapter.ChapterItemAdapter
import pdf.reader.happiness.tools.AchievementUpdater
import pdf.reader.happiness.tools.ChaptersFragmentLocator
import pdf.reader.happiness.tools.CongratulationView
import pdf.reader.happiness.tools.CustomSnackBar
import pdf.reader.happiness.vm.MainFragmentViewModel

@KoinApiExtension
class MainFragment : Fragment(), KoinComponent, ChapterItemAdapter.OnClick,MainFragmentPresenter.MyView {

    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainFragmentViewModel = get()
    private lateinit var chapterItemAdapter: ChapterItemAdapter
    private lateinit var presenter: MainFragmentPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        chapterItemAdapter = ChapterItemAdapter(this)
        presenter = MainFragmentPresenter(this)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rv.adapter = chapterItemAdapter
        binding.rv.isNestedScrollingEnabled = false

    }

    override fun onResume() {
        super.onResume()
        CoroutineScope(Dispatchers.Main).launch {
            update()
        }
    }

    private suspend fun update() {
        viewModel.fetchChapters().observeForever {
            chapterItemAdapter.update(it)
        }
        viewModel.fetchAll().observeForever {
            presenter.updateCoreProgress(it)
        }
    }

    override fun updateCoreProgress(percent: Float) {
        binding.progressCore.setEndProgress(percent)
        binding.progressCore.progress = percent
        binding.progressCore.startProgressAnimation()
    }

    override fun onClick(chapter: ChapterModel) {
        ChaptersFragmentLocator(this, chapter).locateFragment(chapter.fragmentName)
    }

}