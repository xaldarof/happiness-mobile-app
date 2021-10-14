package pdf.reader.happiness.core

import pdf.reader.happiness.data.cache.models.Type
import java.io.Serializable


data class InfoModel (
        val title: String,
        val body: String,
        val favorite: Boolean=false,
        val finished: Boolean=false,
        val isOpened:Boolean=false,
        val type: Type,
        val dataType:Type=Type.CACHE,
        val addedTime:Long = System.currentTimeMillis(),
        val readTimeSeconds:Long = 0,
        val enterCount:Int = 0
): Serializable
