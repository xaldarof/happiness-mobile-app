package pdf.reader.happiness.data.cloud.models

import pdf.reader.happiness.data.cache.models.InfoModelDb
import pdf.reader.happiness.data.cache.models.Type
import java.io.Serializable

data class InfoCloudModel(
    val title: String = "",
    val body: String = "",
    val favorite: Boolean = false,
    val finished: Boolean = false,
    val isOpened: Boolean = false,
    val type: Type = Type.DEFAULT
) : Serializable {


    fun mapToCacheInfoModel(): InfoModelDb {
        return InfoModelDb(title, body, favorite, finished, isOpened, type)
    }
}