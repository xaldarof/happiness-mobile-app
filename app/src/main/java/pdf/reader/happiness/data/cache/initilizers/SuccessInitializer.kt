package pdf.reader.happiness.data.cache.initilizers

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pdf.reader.happiness.data.models.CoreModel
import pdf.reader.happiness.data.models.Type
import pdf.reader.happiness.data.room.dao.CoreDao

interface SuccessInitializer {

    fun init()

    class Base(private val coreDao: CoreDao): SuccessInitializer {
        override fun init() {
            val list = ArrayList<CoreModel>()
            list.add(CoreModel("Что такое успех","success/tobe_success", type = Type.SUCCESS))
            list.add(CoreModel("Продолжим тему: 'Успех'","success/tobe_success_about_success", type = Type.SUCCESS))
            list.add(CoreModel("Уверенность в себе","success/tobe_success_uverenost", type = Type.SUCCESS))
            list.add(CoreModel("Как стать позитивным в жизни","success/tobe_success_positive_at_word", type = Type.SUCCESS))
            list.add(CoreModel("Преодоление сложных ситуаций","success/tobe_succes_win_hard_times", type = Type.SUCCESS))
            list.add(CoreModel("План действий превращения себя в позитивного человека","success/tobe_success_plan", type = Type.SUCCESS))
            list.add(CoreModel("Как стать позитивном","success/tobe_success_positive", type = Type.SUCCESS))
            list.add(CoreModel("30 Цитат для мотивации","success/tobe_success_aforizm", type = Type.SUCCESS))
            list.add(CoreModel("Не будь ленивым","success/tobe_success_lazy", type = Type.SUCCESS))
            list.add(CoreModel("Как стать осознанным","success/tobe_success_practise", type = Type.SUCCESS))
            list.add(CoreModel("Тренинг для мозга","success/tobe_success_trening_mozga", type = Type.SUCCESS))
            list.add(CoreModel("В здоровом теле – здоровый дух","success/tobe_success_goog_food", type = Type.SUCCESS))
            list.add(CoreModel("Медитирование","success/tobe_success_meditation", type = Type.SUCCESS))
            list.add(CoreModel("«Умная» бактерия","success/tobe_success_bacteria.txt", type = Type.SUCCESS))
            list.add(CoreModel("Сон, и еще раз сон","success/tobe_success_sleeping", type = Type.SUCCESS))
            list.add(CoreModel("Тренировка интуиции","success/tobe_success_training_intuition", type = Type.SUCCESS))
            list.add(CoreModel("Изучение иностранного языка","success/tobe_success_new_lang", type = Type.SUCCESS))
            list.add(CoreModel("Классическая музыка","success/tobe_success_classic_music", type = Type.SUCCESS))
            list.add(CoreModel("Как быть более внимательным?","success/vnimatelnost", type = Type.SUCCESS))
            list.add(CoreModel("Харизматичность","success/xarizma", type = Type.SUCCESS))
            list.add(CoreModel("Как стать боллее спокойным","success/spokontost", type = Type.SUCCESS))
            list.add(CoreModel("Откладывай","success/otkladivay", type = Type.SUCCESS))
            list.add(CoreModel("Старайся не брать в долг","success/ne_beri_dolg", type = Type.SUCCESS))
            list.add(CoreModel("Смотрите на мир позитивнее","success/smotri_pozitivne", type = Type.SUCCESS))
            list.add(CoreModel("Научитесь без страха встречать перемены в жизни.","success/vstrecayte_peremeni", type = Type.SUCCESS))
            list.add(CoreModel("Учитесь на собственных ошибках","success/ucis_na_oshibkax", type = Type.SUCCESS))
            list.add(CoreModel("Овладевайте новыми знаниями","success/ucites", type = Type.SUCCESS))
            list.add(CoreModel("Поддерживайте широкий круг общения","success/obsheniya", type = Type.SUCCESS))
            list.add(CoreModel("Не забывайте заботиться о себе.","success/zabotic_o_sebe", type = Type.SUCCESS))


            CoroutineScope(Dispatchers.IO).launch {
                list.forEach { data ->
                    coreDao.insertAll(data)
                }
            }
        }
    }
}