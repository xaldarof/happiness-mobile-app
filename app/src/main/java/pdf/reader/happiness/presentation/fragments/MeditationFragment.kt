package pdf.reader.happiness.presentation.fragments

import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import pdf.reader.happiness.R
import pdf.reader.happiness.core.MusicModel
import pdf.reader.happiness.data.cache.data_source.MusicPathDataSource
import pdf.reader.happiness.data.cache.models.MusicCloudModel
import pdf.reader.happiness.databinding.FragmentMeditationBinding
import pdf.reader.happiness.presentation.adapter.MusicItemAdapter
import pdf.reader.happiness.vm.MeditationFragmentViewModel

@KoinApiExtension
class MeditationFragment : Fragment(), KoinComponent,
    MusicItemAdapter.CallBack,
    MusicPathDataSource.CallBack {

    private lateinit var binding: FragmentMeditationBinding
    private val viewModel: MeditationFragmentViewModel = get()
    private lateinit var itemAdapter: MusicItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMeditationBinding.inflate(inflater, container, false)
        itemAdapter = MusicItemAdapter(this)

        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Toast.makeText(requireContext(), R.string.connect_headphones, Toast.LENGTH_LONG).show()
        binding.rv.adapter = itemAdapter

        CoroutineScope(Dispatchers.IO).launch {
            viewModel.fetchMusics(this@MeditationFragment)
        }
    }

    override fun onSuccess(list: List<MusicCloudModel>) {
        itemAdapter.update(list.map { it.mapToMusic() })
        binding.progressView.visibility = View.INVISIBLE
    }

    override fun onFail() {
        Toast.makeText(requireContext(), R.string.no_connection, Toast.LENGTH_SHORT).show()
    }

    override fun onLoad() {
        binding.progressView.visibility = View.VISIBLE
    }

    private val music = MediaPlayer()
    private var started = false

    override fun onClickPlay(musicModel: MusicModel) {
        if (music.isPlaying) {
            music.stop()
        }
        else {
            if (!started) {
                music.setDataSource(musicModel.url)
                music.prepare()
                music.start()
                started = true
            }else {
                music.setDataSource(musicModel.url)
                music.prepare()
                music.start()
            }
        }
    }
}

//    private suspend fun observePosition() {
//        while (true) {
//            delay(1000)
//            // musicProgressLive.postValue(musicPlayer.currentPosition)
//
//        }
//    }
//}
//        musicPlayer.setOnBufferingUpdateListener { _, i ->
//            val rt = i /100.0
//            val pg = (musicPlayer.duration * rt).toInt()
//            binding.musicProgressBar.secondaryProgress  = pg
//            Log.d("pos","DOWNLOAD = $i")
//        }
//
//        musicProgressLive.observe(this, {
//            binding.musicProgressBar.progress = it
//        })
//