package pdf.reader.happiness.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.delay
import pdf.reader.happiness.data.cache.data_source.MusicPathDataSource
import pdf.reader.happiness.tools.MusicPlayer

class MeditationFragmentViewModel(private val musicPlayer: MusicPlayer,
                                  private val musicPathDataSource: MusicPathDataSource,application: Application):
    AndroidViewModel(application) {

    private var isMoreZero: Boolean = true
    private var second = 60
    private var minute = 2
    private val secondLiveData = MutableLiveData<String>()
    private val minuteLiveData = MutableLiveData<String>()


    fun getSecond(): MutableLiveData<String> {
        return secondLiveData
    }

    fun getMinute(): MutableLiveData<String> {
        return minuteLiveData
    }

    suspend fun startMeditation() {
        val context = getApplication<Application>().applicationContext
        musicPlayer.play(context,musicPathDataSource.fetRandomPath())

        while (isMoreZero) {
            second--
            secondLiveData.postValue(if (second<10) "0$second" else "$second")
            minuteLiveData.postValue(if (minute<10) "0$minute" else "$minute")

            if (second == 0) {
                second = 60
                minute--
            }
            if (minute < 0) {
                isMoreZero = false
                musicPlayer.pause()
            }

            delay(1000)
        }
    }
    fun stopMusic(){
        musicPlayer.pause()
    }
}