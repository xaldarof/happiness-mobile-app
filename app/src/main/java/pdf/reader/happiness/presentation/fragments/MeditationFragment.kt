package pdf.reader.happiness.presentation.fragments

import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import kotlinx.coroutines.*
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import pdf.reader.happiness.R
import pdf.reader.happiness.core.MusicCloudResult
import pdf.reader.happiness.core.MusicModel
import pdf.reader.happiness.databinding.FragmentMeditationBinding
import pdf.reader.happiness.presentation.adapter.MusicItemAdapter
import pdf.reader.happiness.tools.formatToDate
import pdf.reader.happiness.tools.toPlayerFormat
import pdf.reader.happiness.vm.MeditationFragmentViewModel
import java.util.*
import java.util.concurrent.TimeUnit

@KoinApiExtension
class MeditationFragment : Fragment(), KoinComponent,
    MusicItemAdapter.CallBack {

    private lateinit var binding: FragmentMeditationBinding
    private val viewModel: MeditationFragmentViewModel = get()
    private lateinit var itemAdapter: MusicItemAdapter
    private lateinit var mediaPlayer: MediaPlayer
    private var isPlaying = false
    private var isSelected = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMeditationBinding.inflate(inflater, container, false)
        itemAdapter = MusicItemAdapter(this)
        mediaPlayer = MediaPlayer()

        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rv.adapter = itemAdapter
        OverScrollDecoratorHelper.setUpOverScroll(
            binding.rv,
            OverScrollDecoratorHelper.ORIENTATION_VERTICAL
        )

        fetchMusics()

        binding.retryBtn.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                fetchMusics()
                binding.errorLayout.visibility = View.INVISIBLE
            }
        }

        binding.playPauseBtn.setOnClickListener {
            if (isSelected) {
                if (isPlaying) {
                    binding.playPauseBtn.setImageResource(R.drawable.ic_baseline_play_circle_outline_24)
                    mediaPlayer.pause()
                    isPlaying = false
                } else {
                    binding.playPauseBtn.setImageResource(R.drawable.ic_baseline_pause_circle_outline_24)
                    mediaPlayer.start()
                    isPlaying = true
                }
            } else {
                Toast.makeText(requireContext(), "Выберите музыку", Toast.LENGTH_SHORT).show()
            }
        }

        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                if (p2) {
                    mediaPlayer.seekTo(p1)
                }
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })

    }

    private fun fetchMusics() {
        binding.progressView.visibility = View.VISIBLE
        CoroutineScope(Dispatchers.Main).launch {

            when (val result = viewModel.fetchMusics()) {
                is MusicCloudResult.Success -> {
                    itemAdapter.update(result.data.map { it.mapToMusic() })
                    binding.progressView.visibility = View.INVISIBLE
                }
                is MusicCloudResult.Fail -> {
                    binding.errorLayout.visibility = View.VISIBLE
                    binding.progressView.visibility = View.INVISIBLE
                }
            }
        }
    }


    override fun onMusicChange(musicModel: MusicModel) {
        isSelected = true
        if (mediaPlayer.isPlaying) {
            mediaPlayer.pause()
            mediaPlayer.reset()
        } else {
            mediaPlayer.reset()
        }
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)

        mediaPlayer.setDataSource(musicModel.url)
        mediaPlayer.prepareAsync()
        binding.progressView.visibility = View.VISIBLE


        mediaPlayer.setOnPreparedListener {
            binding.progressView.visibility = View.INVISIBLE
            isPlaying = true
            mediaPlayer.start()
            val duration = mediaPlayer.duration

            binding.playPauseBtn.setImageResource(R.drawable.ic_baseline_pause_circle_outline_24)


            binding.durationTv.text = duration.toLong().toPlayerFormat()

            binding.seekBar.max = duration

            CoroutineScope(Dispatchers.Main).launch {
                seekBarObserve()
            }
        }
        mediaPlayer.setOnCompletionListener {
            mediaPlayer.reset()
            isPlaying = false
            binding.playPauseBtn.setImageResource(R.drawable.ic_baseline_play_circle_outline_24)
            isSelected = false
            itemAdapter.onFinish()

        }
    }

    private suspend fun seekBarObserve() {
        while (true) {
            binding.seekBar.progress = mediaPlayer.currentPosition
            delay(1000)
        }
    }

    override fun onStop() {
        super.onStop()
        mediaPlayer.stop()
        isSelected = false
        binding.playPauseBtn.setImageResource(R.drawable.ic_baseline_play_circle_outline_24)
        itemAdapter.onFinish()
    }
}
