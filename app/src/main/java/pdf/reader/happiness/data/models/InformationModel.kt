package pdf.reader.happiness.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "all_data")
data class InformationModel (
    val title: String,
    @PrimaryKey
    val body: String,

    val favorite: Boolean,
    val finished: Boolean
):Serializable