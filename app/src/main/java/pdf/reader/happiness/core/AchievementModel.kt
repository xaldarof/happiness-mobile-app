package pdf.reader.happiness.core

import pdf.reader.happiness.data.cache.models.AchievementModelDb

data class AchievementModel(val message:String,val date:Long,val type:AchievementType) {
    fun mapToAchievementDb():AchievementModelDb{
        return AchievementModelDb(message,date,type)
    }

    enum class AchievementType {
        UNIT_FINISHED,
        TIME_WASTED
    }
}
