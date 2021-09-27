package pdf.reader.happiness.data.cache.initilizers

import pdf.reader.happiness.data.models.CoreModel
import pdf.reader.happiness.data.models.Type
import pdf.reader.happiness.data.room.dao.CoreDao

interface LoveInitializer {

    fun init()

    class Base(private val coreDao: CoreDao): LoveInitializer {
        override fun init() {
            val list = ArrayList<CoreModel>()

            list.add(CoreModel("Что включает в себя любовь","love/cto_vklyucayet_lubov",type = Type.LOVE))
            list.add(CoreModel("Как открыться чувствам","love/kak_vlubitsa",type = Type.LOVE))
            list.add(CoreModel("Вы не можете влюбиться,потому-что...","love/pricini_ne_lyubvi",type = Type.LOVE))
            list.add(CoreModel("Как полюбить человека, который тебя любит","love/kak_polubit",type = Type.LOVE))
            list.add(CoreModel("Цитаты о любви","love/lubov_citati",type = Type.LOVE))
            list.add(CoreModel("Невозможно добывать то, чего нет","love/nevozmojno_dobavit",type = Type.LOVE))
            list.add(CoreModel("Пятый элемент отношений","love/element5",type = Type.LOVE))
            list.add(CoreModel("Любовь воздействует на организм как наркотик","love/lubov_narkotik",type = Type.LOVE))
            list.add(CoreModel("Любовь и шоколад не существуют по отдельности","love/lubov_shakalad_ne_odin",type = Type.LOVE))
            list.add(CoreModel("Обсессивно-компульсивная любовь","love/obsesivnaya_lubov",type = Type.LOVE))
            list.add(CoreModel("Депрессивная любовь","love/depress_lubov",type = Type.LOVE))
            list.add(CoreModel("Маниакальная любовь","love/manikal_lubov",type = Type.LOVE))
            list.add(CoreModel("Бредовая любовь","love/bredovaya_lubov",type = Type.LOVE))
            list.add(CoreModel("Патологическая ревность","love/petologic_lubov",type = Type.LOVE))
            list.add(CoreModel("1 Предварительные различия","love/predvarit_lubov",type = Type.LOVE))
            list.add(CoreModel("2 Любовь как единство","love/lubov_kak_edinstvo",type = Type.LOVE))
            list.add(CoreModel("3 Любовь как неусыпная забота","love/lubov_neuspnaya_rabota",type = Type.LOVE))
            list.add(CoreModel("4 Любовь как оценивание","love/lubov_otsenivaniye",type = Type.LOVE))
            list.add(CoreModel("4.1 Любовь как определение ценности","love/lubov_senosti",type = Type.LOVE))
            list.add(CoreModel("4.2 Любовь как дарение ценности","love/lubov_drevniye_senocti",type = Type.LOVE))
            list.add(CoreModel("4.3 Промежуточное положение?","love/lubov_promejutok",type = Type.LOVE))
            list.add(CoreModel("5. Подходы с точки зрения эмоций","love/lubov_emotsii",type = Type.LOVE))
            list.add(CoreModel("5.1 Любовь как собственно эмоция","love/lubov_emotsii_sobsteniy",type = Type.LOVE))
            list.add(CoreModel("5.2 Любовь как комплекс эмоций","love/lubov_kompleks",type = Type.LOVE))
            list.add(CoreModel("6. Ценность и оправдание любви","love/lubov_cenost",type = Type.LOVE))

            list.forEach { data ->
                coreDao.insertAll(data)
            }
        }
    }
}