package pdf.reader.happiness.data.cache.initilizers


import pdf.reader.happiness.R
import pdf.reader.happiness.data.cache.models.ChapterModelDb
import pdf.reader.happiness.data.cache.models.InfoModelDb
import pdf.reader.happiness.data.cache.models.Type
import pdf.reader.happiness.data.cache.dao.ChaptersDao
import pdf.reader.happiness.data.cache.dao.CoreDao

interface HappyInitializer {

    fun init()

    class Base (private val coreDao: CoreDao,private val chaptersDao: ChaptersDao): HappyInitializer {
        override fun init() {
            val list = ArrayList<InfoModelDb>()
            list.add(InfoModelDb("Что такое счастье","happy/cto_takoy_scaste",type=Type.HAPPY))
            list.add(InfoModelDb("Субъективный взгляд на счастье","happy/subekt_vzglay_na_scaste",type=Type.HAPPY))
            list.add(InfoModelDb("Зависимость счастья от материального благосостояния","happy/scate_ot_materiala",type=Type.HAPPY))
            list.add(InfoModelDb("Какие люди счастливы","happy/kto_budet_scatlivm",type=Type.HAPPY))
            list.add(InfoModelDb("ТОП 20","happy/top20",type=Type.HAPPY))
            list.add(InfoModelDb("Люди о счастье","happy/citati_o_scaste",type=Type.HAPPY))
            list.add(InfoModelDb("Счастье в мелочах","happy/sekreti_scaste",type=Type.HAPPY))
            list.add(InfoModelDb("Где и как найти свое счастье ?","happy/gde_iskat_scaste",type=Type.HAPPY))
            list.add(InfoModelDb("Как начать духовно развиваться","happy/duxovnost",type=Type.HAPPY))
            list.add(InfoModelDb("Слушаем тело","happy/sluwayem_telo",type=Type.HAPPY))
            list.add(InfoModelDb("Лучшая версия себя","happy/lucshaya_versiya_sebya",type=Type.HAPPY))
            list.add(InfoModelDb("Счастье в глазах смотрящего","happy/scaste_v_glazax_smotryashevo",type=Type.HAPPY))

            list.add(InfoModelDb("Цитаты про счастье","happy/cicati100",type=Type.HAPPY))


            list.forEach { data->
                coreDao.insertAll(data)
            }

            chaptersDao.insertChapter(ChapterModelDb("СЧАСТЬЕ",list.size, R.drawable.ic_diagram,0f
                ,fragmentName = ChapterModelDb.FragmentName.HAPPY,colorLight = "#60966f",colorNight = "#24303E"))
        }
    }
}