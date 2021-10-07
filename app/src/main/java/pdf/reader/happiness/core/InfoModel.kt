package pdf.reader.happiness.core

import pdf.reader.happiness.data.models.Type
import java.io.Serializable


data class InfoModel (
        val title: String,
        val body: String,
        val favorite: Boolean=false,
        val finished: Boolean=false,
        val isOpened:Boolean=false,
        val type: Type
    ): Serializable