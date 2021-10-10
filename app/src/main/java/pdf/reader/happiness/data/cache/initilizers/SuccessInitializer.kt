package pdf.reader.happiness.data.cache.initilizers

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pdf.reader.happiness.R
import pdf.reader.happiness.data.cache.models.ChapterModelDb
import pdf.reader.happiness.data.cache.models.InfoModelDb
import pdf.reader.happiness.data.cache.models.Type
import pdf.reader.happiness.data.cache.dao.ChaptersDao
import pdf.reader.happiness.data.cache.dao.CoreDao
import pdf.reader.happiness.tools.PercentCalculator

interface SuccessInitializer {

    fun init()

    class Base(private val coreDao: CoreDao,private val chaptersDao: ChaptersDao): SuccessInitializer {
        override fun init() {
            val list = ArrayList<InfoModelDb>()
            list.add(InfoModelDb("Что такое успех","success/tobe_success", type = Type.SUCCESS))
            list.add(InfoModelDb("Продолжим тему: 'Успех'","success/tobe_success_about_success", type = Type.SUCCESS))
            list.add(InfoModelDb("Уверенность в себе","success/tobe_success_uverenost", type = Type.SUCCESS))
            list.add(InfoModelDb("Как стать позитивным в жизни","success/tobe_success_positive_at_word", type = Type.SUCCESS))
            list.add(InfoModelDb("Преодоление сложных ситуаций","success/tobe_succes_win_hard_times", type = Type.SUCCESS))
            list.add(InfoModelDb("План действий превращения себя в позитивного человека","success/tobe_success_plan", type = Type.SUCCESS))
            list.add(InfoModelDb("Как стать позитивном","success/tobe_success_positive", type = Type.SUCCESS))
            list.add(InfoModelDb("30 Цитат для мотивации","success/tobe_success_aforizm", type = Type.SUCCESS))
            list.add(InfoModelDb("Не будь ленивым","success/tobe_success_lazy", type = Type.SUCCESS))
            list.add(InfoModelDb("Как стать осознанным","success/tobe_success_practise", type = Type.SUCCESS))
            list.add(InfoModelDb("Тренинг для мозга","success/tobe_success_trening_mozga", type = Type.SUCCESS))
            list.add(InfoModelDb("В здоровом теле – здоровый дух","success/tobe_success_goog_food", type = Type.SUCCESS))
            list.add(InfoModelDb("Медитирование","success/tobe_success_meditation", type = Type.SUCCESS))
            list.add(InfoModelDb("«Умная» бактерия","success/tobe_success_bacteria.txt", type = Type.SUCCESS))
            list.add(InfoModelDb("Сон, и еще раз сон","success/tobe_success_sleeping", type = Type.SUCCESS))
            list.add(InfoModelDb("Тренировка интуиции","success/tobe_success_training_intuition", type = Type.SUCCESS))
            list.add(InfoModelDb("Изучение иностранного языка","success/tobe_success_new_lang", type = Type.SUCCESS))
            list.add(InfoModelDb("Классическая музыка","success/tobe_success_classic_music", type = Type.SUCCESS))
            list.add(InfoModelDb("Как быть более внимательным?","success/vnimatelnost", type = Type.SUCCESS))
            list.add(InfoModelDb("Харизматичность","success/xarizma", type = Type.SUCCESS))
            list.add(InfoModelDb("Как стать боллее спокойным","success/spokontost", type = Type.SUCCESS))
            list.add(InfoModelDb("Откладывай","success/otkladivay", type = Type.SUCCESS))
            list.add(InfoModelDb("Старайся не брать в долг","success/ne_beri_dolg", type = Type.SUCCESS))
            list.add(InfoModelDb("Смотрите на мир позитивнее","success/smotri_pozitivne", type = Type.SUCCESS))
            list.add(InfoModelDb("Научитесь без страха встречать перемены в жизни.","success/vstrecayte_peremeni", type = Type.SUCCESS))
            list.add(InfoModelDb("Учитесь на собственных ошибках","success/ucis_na_oshibkax", type = Type.SUCCESS))
            list.add(InfoModelDb("Овладевайте новыми знаниями","success/ucites", type = Type.SUCCESS))
            list.add(InfoModelDb("Поддерживайте широкий круг общения","success/obsheniya", type = Type.SUCCESS))
            list.add(InfoModelDb("Не забывайте заботиться о себе.","success/zabotic_o_sebe", type = Type.SUCCESS))


            CoroutineScope(Dispatchers.IO).launch {
                list.forEach { data ->
                    coreDao.insertAll(data)
                }
                chaptersDao.insertChapter(ChapterModelDb("УСПЕХ",list.size, R.drawable.ic_goal,0f,
                    fragmentName = ChapterModelDb.FragmentName.SUCCESS,colorLight = "#D4ad2b",colorNight = "#24303E"))

                val chapter = coreDao.fetchSuccessCount(Type.SUCCESS)
                chaptersDao.updateChapterSize(chapter.size,"УСПЕХ")
                chaptersDao
                    .updateChapterProgress(
                        PercentCalculator
                            .Base()
                            .calculatePercent(chapter.map { it.mapToInfoModel() }),"УСПЕХ")            }
        }
    }
}