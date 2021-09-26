package pdf.reader.happiness.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "love")
data class LoveModel (
    val title: String,
    @PrimaryKey
    val body: String,

    val favorite: Boolean,
    val finished: Boolean
): Serializable