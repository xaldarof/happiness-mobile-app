package pdf.reader.happiness.data.cache.models

import pdf.reader.happiness.core.MusicModel
import java.io.Serializable

data class MusicCloudModel(val name: String = "", val url: String = "",val dur:String="") : Serializable {
    fun mapToMusic():MusicModel {
        return MusicModel(name,url,duration = dur)
    }
}