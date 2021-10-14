package pdf.reader.happiness.tools

import pdf.reader.happiness.core.Name
import pdf.reader.happiness.data.cache.models.Type

fun Type.translateToRu():String{
    if (this==Type.HAPPY) return Name.HAPPY
    if (this==Type.SUCCESS) return Name.SUCCESS
    if (this==Type.LIFE) return Name.LIFE
    else return Name.LOVE
}