package pdf.reader.happiness.data.cache.initilizers

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pdf.reader.happiness.R
import pdf.reader.happiness.data.models.ChapterModelDb
import pdf.reader.happiness.data.models.CoreModel
import pdf.reader.happiness.data.models.Type
import pdf.reader.happiness.data.dao.ChaptersDao
import pdf.reader.happiness.data.dao.CoreDao

interface LifeInitializer {

    fun init()

    class Base(private val coreDao: CoreDao,private val chaptersDao: ChaptersDao) : LifeInitializer {
        override fun init() {
            val list = ArrayList<CoreModel>()
            list.add(CoreModel("Перестань себя ЖАЛЕТЬ", "life/ne_jaley_sebya", type = Type.LIFE))
            list.add(CoreModel("Мотивация в книгах.", "life/motivatsita_v_knigah", type = Type.LIFE))
            list.add(CoreModel("Самообучение.", "life/samoobuceniye", type = Type.LIFE))
            list.add(CoreModel("Места где происходит «магия»", "life/mesto_gde_proisxodiy_magia", type = Type.LIFE))
            list.add(CoreModel("Трудное детство.", "life/hard_detstvo", type = Type.LIFE))
            list.add(CoreModel("Окружение что тебя формирует.(1)", "life/okrujeniye_formiruyet1", type = Type.LIFE))
            list.add(CoreModel("Окружение что тебя формирует.(2)", "life/okrujeniye_formiruyet2", type = Type.LIFE))
            list.add(CoreModel("Синдром самозванца.", "life/sindrom_savozvansa", type = Type.LIFE))
            list.add(CoreModel("Нытьё и мотивация.", "life/nityo_motivatsiya", type = Type.LIFE))
            list.add(CoreModel("Работа на дядю.", "life/rabota_na_dyadyu", type = Type.LIFE))
            list.add(CoreModel("Мы все умрем.", "life/mi_vse_umryom", type = Type.LIFE))
            list.add(CoreModel("Справедливости не существует", "life/spravedlivosti_net", type = Type.LIFE))
            list.add(CoreModel("Пустота на личном фронте.", "life/pustota_na_fronte", type = Type.LIFE))
            list.add(CoreModel("Уверенность в себе и компетенция.", "life/uverenost_v_sebe", type = Type.LIFE))
            list.add(CoreModel("Ощущение вкуса жизни.", "life/vkus_jizni", type = Type.LIFE))
            list.add(CoreModel("Многоточие", "life/mnogotociye", type = Type.LIFE))
            list.add(CoreModel("Выгорание", "life/vigoraniye", type = Type.LIFE))
            list.add(CoreModel("Путь или результат? ", "life/put_ili_resultat", type = Type.LIFE))
            list.add(CoreModel(" А ты что ли не все?", "life/a_ti_cto_nevse", type = Type.LIFE))
            list.add(CoreModel("Неумение держать себя в руках.", "life/ne_umeniye_derjat_sebya", type = Type.LIFE))
            list.add(CoreModel("Молодежь", "life/molodyoj", type = Type.LIFE))
            list.add(CoreModel("Ты никогда не узнаешь правды.", "life/nikogda_ne_uznayesh_pravdu", type = Type.LIFE))
            list.add(CoreModel("Идеальная жизнь", "life/ideal_jizn", type = Type.LIFE))
            list.add(CoreModel("Умение адаптироваться.", "life/umeniye_adaptirovatsa", type = Type.LIFE))
            list.add(CoreModel("Смысл дружбы.", "life/smisl_drujbi", type = Type.LIFE))
            list.add(CoreModel("Кто ? ", "life/kto_vi", type = Type.LIFE))
            list.add(CoreModel("Люди любят откровения.", "life/lyudo_otkroveniya", type = Type.LIFE))
            list.add(CoreModel("Пох*исты живут счастливо", "life/poxuisti_jivut_krasivo", type = Type.LIFE))
            list.add(CoreModel("Книги", "life/knigi", type = Type.LIFE))
            list.add(CoreModel("О саморазвитии", "life/samorazvitiye", type = Type.LIFE))
            list.add(CoreModel("О лженауках", "life/lgenauka", type = Type.LIFE))
            list.add(CoreModel("Манеры", "life/maneri", type = Type.LIFE))
            list.add(CoreModel("Бездельник", "life/bezdelniki", type = Type.LIFE))
            list.add(CoreModel("Плохо делать поспешные выводы", "life/pospeshniye_vivodi", type = Type.LIFE))
            list.add(CoreModel("Как развить в себе харизму?", "life/xarizma", type = Type.LIFE))
            list.add(CoreModel("О медицине", "life/medicina", type = Type.LIFE))
            list.add(CoreModel("Неумение отстоять свою точку зрения.", "life/tocka_zraniya", type = Type.LIFE))
            list.add(CoreModel("Безответственные", "life/bezotstveniye", type = Type.LIFE))


            CoroutineScope(Dispatchers.IO).launch {
                list.forEach { life ->
                    coreDao.insertAll(life)
                }
                chaptersDao.insertChapter(ChapterModelDb("ЖИЗНЬ",list.size, R.drawable.ic_goldfish,0f
                ,fragmentName = ChapterModelDb.FragmentName.LIFE,colorLight = "#607196",colorNight = "#24303E"))
            }

        }
    }
}