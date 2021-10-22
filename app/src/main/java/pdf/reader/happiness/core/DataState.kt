package pdf.reader.happiness.core

import pdf.reader.happiness.data.cache.models.MusicCloudModel
import pdf.reader.happiness.data.cloud.models.TokenCloudModel


sealed class TokenCloudResult{
    data class Success(val data:TokenCloudModel):TokenCloudResult()
    data class Fail(val message:String):TokenCloudResult()
}

sealed class MusicCloudResult{
    data class Success(val data:List<MusicCloudModel>):MusicCloudResult()
    data class Fail(val message:String):MusicCloudResult()
}


enum class Status{
    ERROR,
    SUCCESS
}
