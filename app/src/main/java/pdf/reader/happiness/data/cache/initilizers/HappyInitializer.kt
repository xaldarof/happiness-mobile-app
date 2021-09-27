package pdf.reader.happiness.data.cache.initilizers

import pdf.reader.happiness.data.models.CoreModel
import pdf.reader.happiness.data.models.InfoModel
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
            list.add(CoreModel("sas","",type=Type.HAPPY))
            list.add(CoreModel("","",type=Type.HAPPY))
            list.add(CoreModel("","",type=Type.HAPPY))


            list.forEach { data->
                coreDao.insertAll(data)
            }
        }
    }
}