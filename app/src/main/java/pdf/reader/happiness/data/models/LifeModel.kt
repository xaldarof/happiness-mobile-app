package pdf.reader.happiness.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "life")
data class LifeModel (
    val title: String,
    @PrimaryKey
    val body: String,

    val favorite: Boolean=false,
    val finished: Boolean=false,
    val isOpened:Boolean=false
): Serializable {

    fun mapToInfoModel() : InfoModel {
        return InfoModel(title, body, favorite, finished)
    }
}