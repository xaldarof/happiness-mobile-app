package pdf.reader.happiness.vm

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import pdf.reader.happiness.core.MusicModel
import pdf.reader.happiness.data.cache.data_source.MusicPathDataSource
import pdf.reader.happiness.data.cache.models.MusicCloudModel
import pdf.reader.happiness.tools.CallBack
import pdf.reader.happiness.tools.MusicPlayer

class MeditationFragmentViewModel(
    private val musicPlayer: MusicPlayer,
    private val musicPathDataSource: MusicPathDataSource,
    application: Application
) : AndroidViewModel(application) {


    suspend fun fetchMusics(callBack: MusicPathDataSource.CallBack) = musicPathDataSource.fetMusics(callBack)

    suspend fun addMusic(musicModel: MusicModel) = musicPathDataSource.addMusic(musicModel.mapToCloud())


    //fun stopMusic() = musicPlayer.pauseMusic()
}