package pdf.reader.happiness.core

import pdf.reader.happiness.data.models.AchievementModelDb

data class AchievementModel(val message:String,val date:Long) {
    fun mapToAchievementDb():AchievementModelDb{
        return AchievementModelDb(message,date)
    }
}