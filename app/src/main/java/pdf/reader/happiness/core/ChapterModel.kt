package pdf.reader.happiness.core

import pdf.reader.happiness.data.models.ChapterModelDb
import pdf.reader.happiness.presentation.fragments.SuccessFragment
import java.io.Serializable

data class ChapterModel(
    val name:String,
    val size:Int,
    val image:Int,
    val progress:Float,
    val isFinished:Boolean=false,
    val isCongratulated:Boolean=false,
    val fragmentName:ChapterModelDb.FragmentName
):Serializable