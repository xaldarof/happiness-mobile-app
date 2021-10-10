package pdf.reader.happiness.data.cache.initilizers

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pdf.reader.happiness.R
import pdf.reader.happiness.core.Name
import pdf.reader.happiness.data.cache.models.ChapterModelDb
import pdf.reader.happiness.data.cache.models.InfoModelDb
import pdf.reader.happiness.data.cache.models.Type
import pdf.reader.happiness.data.cache.dao.ChaptersDao
import pdf.reader.happiness.data.cache.dao.CoreDao
import pdf.reader.happiness.tools.PercentCalculator

interface LoveInitializer {

    fun init()

    class Base(private val coreDao: CoreDao,private val chaptersDao: ChaptersDao): LoveInitializer {
        override fun init() {
            val list = ArrayList<InfoModelDb>()

            list.add(InfoModelDb("Что включает в себя любовь", "love/cto_vklyucayet_lubov", type = Type.LOVE))
            list.add(InfoModelDb("Как открыться чувствам", "love/kak_vlubitsa", type = Type.LOVE))
            list.add(InfoModelDb("Вы не можете влюбиться,потому-что...", "love/pricini_ne_lyubvi", type = Type.LOVE))
            list.add(InfoModelDb("Как полюбить человека, который тебя любит", "love/kak_polubit", type = Type.LOVE))
            list.add(InfoModelDb("Цитаты о любви", "love/lubov_citati", type = Type.LOVE))
            list.add(InfoModelDb("Невозможно добывать то, чего нет", "love/nevozmojno_dobavit", type = Type.LOVE))
            list.add(InfoModelDb("Пятый элемент отношений", "love/element5", type = Type.LOVE))
            list.add(InfoModelDb("Любовь воздействует на организм как наркотик", "love/lubov_narkotik", type = Type.LOVE))
            list.add(InfoModelDb("Любовь и шоколад не существуют по отдельности", "love/lubov_shakalad_ne_odin", type = Type.LOVE))
            list.add(InfoModelDb("Обсессивно-компульсивная любовь", "love/obsesivnaya_lubov", type = Type.LOVE))
            list.add(InfoModelDb("Депрессивная любовь", "love/depress_lubov", type = Type.LOVE))
            list.add(InfoModelDb("Маниакальная любовь", "love/manikal_lubov", type = Type.LOVE))
            list.add(InfoModelDb("Бредовая любовь", "love/bredovaya_lubov", type = Type.LOVE))
            list.add(InfoModelDb("Патологическая ревность", "love/petologic_lubov", type = Type.LOVE))
            list.add(InfoModelDb("1 Предварительные различия", "love/predvarit_lubov", type = Type.LOVE))
            list.add(InfoModelDb("2 Любовь как единство", "love/lubov_kak_edinstvo", type = Type.LOVE))
            list.add(InfoModelDb("3 Любовь как неусыпная забота", "love/lubov_neuspnaya_rabota", type = Type.LOVE))
            list.add(InfoModelDb("4 Любовь как оценивание", "love/lubov_otsenivaniye", type = Type.LOVE))
            list.add(InfoModelDb("4.1 Любовь как определение ценности", "love/lubov_senosti", type = Type.LOVE))
            list.add(InfoModelDb("4.2 Любовь как дарение ценности", "love/lubov_drevniye_senocti", type = Type.LOVE))
            list.add(InfoModelDb("4.3 Промежуточное положение?", "love/lubov_promejutok", type = Type.LOVE))
            list.add(InfoModelDb("5. Подходы с точки зрения эмоций", "love/lubov_emotsii", type = Type.LOVE))
            list.add(InfoModelDb("5.1 Любовь как собственно эмоция", "love/lubov_emotsii_sobsteniy", type = Type.LOVE))
            list.add(InfoModelDb("5.2 Любовь как комплекс эмоций", "love/lubov_kompleks", type = Type.LOVE))
            list.add(InfoModelDb("6. Ценность и оправдание любви", "love/lubov_cenost", type = Type.LOVE))

            CoroutineScope(Dispatchers.IO).launch {
                list.forEach { data ->
                    coreDao.insertAll(data)
                }
                chaptersDao.insertChapter(ChapterModelDb(Name.LOVE, list.size, R.drawable.ic_talk, 0f, fragmentName =
                ChapterModelDb.FragmentName.LOVE, colorLight = "#B32438", colorNight = "#24303E"))

                val chapter = coreDao.fetchLoveCount(Type.LOVE)
                chaptersDao.updateChapterSize(chapter.size, Name.LOVE)
                chaptersDao
                    .updateChapterProgress(
                        PercentCalculator
                            .Base()
                            .calculatePercent(chapter.map { it.mapToInfoModel() }),Name.LOVE)
            }
        }
    }
}