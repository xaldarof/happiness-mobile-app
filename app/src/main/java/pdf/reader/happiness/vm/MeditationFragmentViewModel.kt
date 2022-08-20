package pdf.reader.happiness.vm

import android.app.Application
import androidx.lifecycle.*
import pdf.reader.happiness.core.MusicModel
import pdf.reader.happiness.data.cache.data_source.MusicPathDataSource

import pdf.reader.happiness.tools.MusicPlayer

class MeditationFragmentViewModel(
    private val musicPlayer: MusicPlayer,
    private val musicPathDataSource: MusicPathDataSource,
    application: Application
) : AndroidViewModel(application) {


    suspend fun fetchMusics() = musicPathDataSource.fetMusics()

    suspend fun addMusic(musicModel: MusicModel) = musicPathDataSource.addMusic(musicModel.mapToCloud())

}