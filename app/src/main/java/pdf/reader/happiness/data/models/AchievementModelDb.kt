package pdf.reader.happiness.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import pdf.reader.happiness.core.AchievementModel
import java.io.Serializable

@Entity(tableName = "ach")
data class AchievementModelDb (
    @PrimaryKey
    val message:String,
    val date:Long,
    val type:AchievementModel.AchievementType
): Serializable {

    fun mapToAchievementModel() : AchievementModel = AchievementModel(message,date,type)

}