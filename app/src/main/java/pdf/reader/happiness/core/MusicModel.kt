package pdf.reader.happiness.core

import pdf.reader.happiness.data.cache.models.MusicCloudModel

data class MusicModel(val name:String,val url:String,var isPlaying:Boolean=false) {
    fun mapToCloud():MusicCloudModel {
        return MusicCloudModel(name, url)
    }
}