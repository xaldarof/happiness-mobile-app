package pdf.reader.happiness.data.cache.initilizers

import pdf.reader.happiness.data.models.InformationModel
import pdf.reader.happiness.data.room.MainDao

interface SuccessInitializer {

    fun init()

    class Base(private val dao: MainDao): SuccessInitializer {
        override fun init() {
            val list = ArrayList<InformationModel>()
            list.add(InformationModel("Что такое успех","tobe_success.txt",false,false))
            list.add(InformationModel("Продолжим тему: 'Успех'","tobe_success_about_success",false,false))
            list.add(InformationModel("Уверенность в себе","tobe_success_uverenost",false,false))
            list.add(InformationModel("Не будь ленивым","tobe_success_lazy",false,false))
            list.add(InformationModel("Как стать осознанным","tobe_success_practise.txt",false,false))
            list.add(InformationModel("Тренинг для мозга","tobe_success_trening_mozga",false,false))
            list.add(InformationModel("В здоровом теле – здоровый дух","tobe_success_goog_food",false,false))
            list.add(InformationModel("Медитирование","tobe_success_meditation",false,false))
            list.add(InformationModel("«Умная» бактерия","tobe_success_bacteria",false,false))
            list.add(InformationModel("Сон, и еще раз сон","tobe_success_sleeping",false,false))
            list.add(InformationModel("Тренировка интуиции","tobe_success_training_intuition",false,false))
            list.add(InformationModel("Изучение иностранного языка","tobe_success_new_lang",false,false))
            list.add(InformationModel("Классическая музыка","tobe_success_classic_music",false,false))

            list.forEach { successModel ->
                dao.insertSuccess(successModel)
            }
        }
    }
}