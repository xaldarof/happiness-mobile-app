package pdf.reader.happiness.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import pdf.reader.happiness.R
import pdf.reader.happiness.core.ChapterModel

@Entity(tableName = "chapters")
data class ChapterModelDb (
    @PrimaryKey
    val name:String,

    val size:Int,
    val image:Int,
    val progress:Float,
    val isFinished:Boolean = false,
    val isCongratulated:Boolean=false,
    val fragmentName:FragmentName,
    val colorLight:String,
    val colorNight:String
){
    fun mapToChapter():ChapterModel {
        return ChapterModel(name, size, image, progress,isFinished,isCongratulated,fragmentName,colorLight,colorNight)
    }
    enum class FragmentName{
        SUCCESS,
        LIFE,
        LOVE,
        HAPPY
    }
}