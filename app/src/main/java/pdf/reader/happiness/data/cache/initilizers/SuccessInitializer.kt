package pdf.reader.happiness.data.cache.initilizers

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pdf.reader.happiness.data.models.SuccessModel
import pdf.reader.happiness.data.room.dao.SuccessDao

interface SuccessInitializer {

    fun init()

    class Base(private val dao: SuccessDao): SuccessInitializer {
        override fun init() {
            val list = ArrayList<SuccessModel>()
            list.add(SuccessModel("Что такое успех","success/tobe_success"))
            list.add(SuccessModel("Продолжим тему: 'Успех'","success/tobe_success_about_success"))
            list.add(SuccessModel("Уверенность в себе","success/tobe_success_uverenost"))
            list.add(SuccessModel("Как стать позитивным в жизни","success/tobe_success_positive_at_word"))
            list.add(SuccessModel("Преодоление сложных ситуаций","success/tobe_succes_win_hard_times"))
            list.add(SuccessModel("План действий превращения себя в позитивного человека","success/tobe_success_plan"))
            list.add(SuccessModel("Как стать позитивном","success/tobe_success_positive"))
            list.add(SuccessModel("30 Цитат для мотивации","success/tobe_success_aforizm"))
            list.add(SuccessModel("Не будь ленивым","success/tobe_success_lazy"))
            list.add(SuccessModel("Как стать осознанным","success/tobe_success_practise"))
            list.add(SuccessModel("Тренинг для мозга","success/tobe_success_trening_mozga"))
            list.add(SuccessModel("В здоровом теле – здоровый дух","success/tobe_success_goog_food"))
            list.add(SuccessModel("Медитирование","success/tobe_success_meditation",))
            list.add(SuccessModel("«Умная» бактерия","success/tobe_success_bacteria.txt"))
            list.add(SuccessModel("Сон, и еще раз сон","success/tobe_success_sleeping"))
            list.add(SuccessModel("Тренировка интуиции","success/tobe_success_training_intuition"))
            list.add(SuccessModel("Изучение иностранного языка","success/tobe_success_new_lang"))
            list.add(SuccessModel("Классическая музыка","success/tobe_success_classic_music"))
            list.add(SuccessModel("Как быть более внимательным?","success/vnimatelnost"))
            list.add(SuccessModel("Харизматичность","success/xarizma"))
            list.add(SuccessModel("Как стать боллее спокойным","success/spokontost"))
            list.add(SuccessModel("Откладывай","success/otkladivay"))
            list.add(SuccessModel("Старайся не брать в долг","success/ne_beri_dolg"))
            list.add(SuccessModel("Смотрите на мир позитивнее","success/smotri_pozitivne"))
            list.add(SuccessModel("Научитесь без страха встречать перемены в жизни.","success/vstrecayte_peremeni"))
            list.add(SuccessModel("Учитесь на собственных ошибках","success/ucis_na_oshibkax"))
            list.add(SuccessModel("Овладевайте новыми знаниями","success/ucites"))
            list.add(SuccessModel("Поддерживайте широкий круг общения","success/obsheniya"))
            list.add(SuccessModel("Не забывайте заботиться о себе.","success/zabotic_o_sebe"))


            CoroutineScope(Dispatchers.IO).launch {
                list.forEach { successModel ->
                    dao.insertSuccess(successModel)
                }
            }
        }
    }
}