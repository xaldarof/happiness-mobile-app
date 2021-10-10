package pdf.reader.happiness.tools

import pdf.reader.happiness.data.cache.models.Type

class TypeLocator {

    fun locate(type: String):Type {
        if (type == "ЖИЗНЬ") return Type.LIFE
        if (type == "ЛЮБОВЬ") return Type.LOVE
        if (type == "УСПЕХ") return Type.SUCCESS
        if (type == "СЧАСТЬЕ") return Type.HAPPY

        else return Type.SUCCESS
    }
}