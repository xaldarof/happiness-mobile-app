package pdf.reader.happiness.data.models

import java.io.Serializable

data class InfoModel(
    val title: String,
    val body: String,
    val favorite: Boolean,
    val finished: Boolean
):Serializable