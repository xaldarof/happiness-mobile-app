package pdf.reader.happiness.data.cloud.models

import pdf.reader.happiness.core.InfoModel
import pdf.reader.happiness.data.cache.models.InfoModelDb
import pdf.reader.happiness.data.cache.models.Type
import java.io.Serializable

data class InfoCloudModel(
    val title: String = "",
    val body: String = "",
    val favorite: Boolean = false,
    val finished: Boolean = false,
    val isOpened: Boolean = false,
    val type: Type = Type.DEFAULT,
    val dataType:Type = Type.CLOUD,
    val addedTime:Long = System.currentTimeMillis(),
    val access:Boolean = false

) : Serializable {


    fun mapToCacheInfoModel(): InfoModelDb {
        return InfoModelDb(title, body, favorite, finished, isOpened, type,dataType,addedTime)
    }
    fun mapToInfoModel(): InfoModel{
        return InfoModel(title, body, favorite, finished, isOpened, type,dataType,addedTime)
    }
}