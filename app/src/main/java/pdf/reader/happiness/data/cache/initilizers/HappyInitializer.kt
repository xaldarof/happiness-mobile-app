package pdf.reader.happiness.data.cache.initilizers

import pdf.reader.happiness.data.models.CoreModel
import pdf.reader.happiness.data.models.Type
import pdf.reader.happiness.data.room.dao.CoreDao

interface HappyInitializer {

    fun init()

    class Base (private val coreDao: CoreDao): HappyInitializer {
        override fun init() {
            val list = ArrayList<CoreModel>()
            list.add(CoreModel("Что такое счастье","happy/cto_takoy_scaste",type=Type.HAPPY))
            list.add(CoreModel("Субъективный взгляд на счастье","happy/subekt_vzglay_na_scaste",type=Type.HAPPY))
            list.add(CoreModel("Зависимость счастья от материального благосостояния","happy/scate_ot_materiala",type=Type.HAPPY))
            list.add(CoreModel("Какие люди счастливы","happy/kto_budet_scatlivm",type=Type.HAPPY))
            list.add(CoreModel("ТОП 20","happy/top20",type=Type.HAPPY))
            list.add(CoreModel("Люди о счастье","happy/citati_o_scaste",type=Type.HAPPY))
            list.add(CoreModel("Счастье в мелочах","happy/sekreti_scaste",type=Type.HAPPY))
            list.add(CoreModel("Где и как найти свое счастье ?","happy/gde_iskat_scaste",type=Type.HAPPY))
            list.add(CoreModel("Как начать духовно развиваться","happy/duxovnost",type=Type.HAPPY))
            list.add(CoreModel("Слушаем тело","happy/sluwayem_telo",type=Type.HAPPY))
            list.add(CoreModel("Лучшая версия себя","happy/lucshaya_versiya_sebya",type=Type.HAPPY))
            list.add(CoreModel("Счастье в глазах смотрящего","happy/scaste_v_glazax_smotryashevo",type=Type.HAPPY))

            list.add(CoreModel("Цитаты про счастье","happy/cicati100",type=Type.HAPPY))
            list.add(CoreModel("Слушаем тело","happy/sluwayem_telo",type=Type.HAPPY))
            list.add(CoreModel("Слушаем тело","happy/sluwayem_telo",type=Type.HAPPY))
            list.add(CoreModel("Слушаем тело","happy/sluwayem_telo",type=Type.HAPPY))


            list.forEach { data->
                coreDao.insertAll(data)
            }
        }
    }
}