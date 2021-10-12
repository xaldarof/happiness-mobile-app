package pdf.reader.happiness.data.cache.initilizers

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pdf.reader.happiness.R
import pdf.reader.happiness.core.FragmentName
import pdf.reader.happiness.core.Name
import pdf.reader.happiness.data.cache.models.ChapterModelDb
import pdf.reader.happiness.data.cache.models.InfoModelDb
import pdf.reader.happiness.data.cache.models.Type
import pdf.reader.happiness.data.cache.dao.ChaptersDao
import pdf.reader.happiness.data.cache.dao.CoreDao
import pdf.reader.happiness.tools.PercentCalculator

interface LifeInitializer {

    fun init()

    class Base(private val coreDao: CoreDao,private val chaptersDao: ChaptersDao) : LifeInitializer {
        override fun init() {
            val list = ArrayList<InfoModelDb>()
            list.add(InfoModelDb("Перестань себя ЖАЛЕТЬ", "life/ne_jaley_sebya", type = Type.LIFE))
            list.add(InfoModelDb("Мотивация в книгах.", "life/motivatsita_v_knigah", type = Type.LIFE))
            list.add(InfoModelDb("Самообучение.", "life/samoobuceniye", type = Type.LIFE))
            list.add(InfoModelDb("Места где происходит «магия»", "life/mesto_gde_proisxodiy_magia", type = Type.LIFE))
            list.add(InfoModelDb("Трудное детство.", "life/hard_detstvo", type = Type.LIFE))
            list.add(InfoModelDb("Окружение что тебя формирует.(1)", "life/okrujeniye_formiruyet1", type = Type.LIFE))
            list.add(InfoModelDb("Окружение что тебя формирует.(2)", "life/okrujeniye_formiruyet2", type = Type.LIFE))
            list.add(InfoModelDb("Синдром самозванца.", "life/sindrom_savozvansa", type = Type.LIFE))
            list.add(InfoModelDb("Нытьё и мотивация.", "life/nityo_motivatsiya", type = Type.LIFE))
            list.add(InfoModelDb("Работа на дядю.", "life/rabota_na_dyadyu", type = Type.LIFE))
            list.add(InfoModelDb("Мы все умрем.", "life/mi_vse_umryom", type = Type.LIFE))
            list.add(InfoModelDb("Справедливости не существует", "life/spravedlivosti_net", type = Type.LIFE))
            list.add(InfoModelDb("Пустота на личном фронте.", "life/pustota_na_fronte", type = Type.LIFE))
            list.add(InfoModelDb("Уверенность в себе и компетенция.", "life/uverenost_v_sebe", type = Type.LIFE))
            list.add(InfoModelDb("Ощущение вкуса жизни.", "life/vkus_jizni", type = Type.LIFE))
            list.add(InfoModelDb("Многоточие", "life/mnogotociye", type = Type.LIFE))
            list.add(InfoModelDb("Выгорание", "life/vigoraniye", type = Type.LIFE))
            list.add(InfoModelDb("Путь или результат? ", "life/put_ili_resultat", type = Type.LIFE))
            list.add(InfoModelDb(" А ты что ли не все?", "life/a_ti_cto_nevse", type = Type.LIFE))
            list.add(InfoModelDb("Неумение держать себя в руках.", "life/ne_umeniye_derjat_sebya", type = Type.LIFE))
            list.add(InfoModelDb("Молодежь", "life/molodyoj", type = Type.LIFE))
            list.add(InfoModelDb("Ты никогда не узнаешь правды.", "life/nikogda_ne_uznayesh_pravdu", type = Type.LIFE))
            list.add(InfoModelDb("Идеальная жизнь", "life/ideal_jizn", type = Type.LIFE))
            list.add(InfoModelDb("Умение адаптироваться.", "life/umeniye_adaptirovatsa", type = Type.LIFE))
            list.add(InfoModelDb("Смысл дружбы.", "life/smisl_drujbi", type = Type.LIFE))
            list.add(InfoModelDb("Кто ? ", "life/kto_vi", type = Type.LIFE))
            list.add(InfoModelDb("Люди любят откровения.", "life/lyudo_otkroveniya", type = Type.LIFE))
            list.add(InfoModelDb("Пох*исты живут счастливо", "life/poxuisti_jivut_krasivo", type = Type.LIFE))
            list.add(InfoModelDb("Книги", "life/knigi", type = Type.LIFE))
            list.add(InfoModelDb("О саморазвитии", "life/samorazvitiye", type = Type.LIFE))
            list.add(InfoModelDb("О лженауках", "life/lgenauka", type = Type.LIFE))
            list.add(InfoModelDb("Манеры", "life/maneri", type = Type.LIFE))
            list.add(InfoModelDb("Бездельник", "life/bezdelniki", type = Type.LIFE))
            list.add(InfoModelDb("Плохо делать поспешные выводы", "life/pospeshniye_vivodi", type = Type.LIFE))
            list.add(InfoModelDb("Как развить в себе харизму?", "life/xarizma", type = Type.LIFE))
            list.add(InfoModelDb("О медицине", "life/medicina", type = Type.LIFE))
            list.add(InfoModelDb("Неумение отстоять свою точку зрения.", "life/tocka_zraniya", type = Type.LIFE))
            list.add(InfoModelDb("Безответственные", "life/bezotstveniye", type = Type.LIFE))


            CoroutineScope(Dispatchers.IO).launch {
                list.forEach { life ->
                    coreDao.insertAll(life)
                }
                chaptersDao.insertChapter(ChapterModelDb(Name.LIFE,list.size, R.drawable.ic_goldfish,0f
                ,fragmentName = FragmentName.LIFE,colorLight = "#607196",colorNight = "#24303E"))

                val chapter = coreDao.fetchLifeCount(Type.LIFE)
                chaptersDao.updateChapterSize(chapter.size, Name.LIFE)
                chaptersDao
                    .updateChapterProgress(
                        PercentCalculator
                        .Base()
                        .calculatePercent(chapter.map { it.mapToInfoModel() }),Name.LIFE)
            }

        }
    }
}