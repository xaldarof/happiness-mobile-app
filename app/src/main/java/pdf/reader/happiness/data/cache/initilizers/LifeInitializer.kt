package pdf.reader.happiness.data.cache.initilizers

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pdf.reader.happiness.data.models.LifeModel
import pdf.reader.happiness.data.room.dao.LifeDao
import pdf.reader.happiness.tools.AssetReader

interface LifeInitializer {

    fun init()

    class Base(private val dao: LifeDao) : LifeInitializer {
        override fun init() {
            val list = ArrayList<LifeModel>()
            list.add(LifeModel("Перестань себя ЖАЛЕТЬ", "life/ne_jaley_sebya"))
            list.add(LifeModel("Мотивация в книгах.", "life/motivatsita_v_knigah"))
            list.add(LifeModel("Самообучение.", "life/samoobuceniye"))
            list.add(LifeModel("Места где происходит «магия»", "life/mesto_gde_proisxodiy_magia"))
            list.add(LifeModel("Трудное детство.", "life/hard_detstvo"))
            list.add(LifeModel("Окружение что тебя формирует.(1)", "life/okrujeniye_formiruyet1"))
            list.add(LifeModel("Окружение что тебя формирует.(2)", "life/okrujeniye_formiruyet2"))
            list.add(LifeModel("Синдром самозванца.", "life/sindrom_savozvansa"))
            list.add(LifeModel("Нытьё и мотивация.", "life/nityo_motivatsiya"))
            list.add(LifeModel("Работа на дядю.", "life/rabota_na_dyadyu"))
            list.add(LifeModel("Мы все умрем.", "life/mi_vse_umryom"))
            list.add(LifeModel("Справедливости не существует", "life/spravedlivosti_net"))
            list.add(LifeModel("Пустота на личном фронте.", "life/pustota_na_fronte"))
            list.add(LifeModel("Уверенность в себе и компетенция.", "life/uverenost_vsebe"))
            list.add(LifeModel("Ощущение вкуса жизни.", "life/vkus_jizni"))
            list.add(LifeModel("Многоточие", "life/mnogotociye"))
            list.add(LifeModel("Выгорание", "life/vigoraniye"))
            list.add(LifeModel("Путь или результат? ", "life/put_ili_resultat"))
            list.add(LifeModel(" А ты что ли не все?", "life/a_ti_cto_nevse"))
            list.add(LifeModel("Неумение держать себя в руках.", "life/ne_umeniye_derjat_sebya"))
            list.add(LifeModel("Молодежь", "life/molodyoj"))
            list.add(LifeModel("Ты никогда не узнаешь правды.", "life/nikogda_ne_uznayesh_pravdu"))
            list.add(LifeModel("Идеальная жизнь", "life/ideal_jizn"))
            list.add(LifeModel("Умение адаптироваться.", "life/umeniye_adaptirovatsa"))
            list.add(LifeModel("Смысл дружбы.", "life/smisl_drujbi"))
            list.add(LifeModel("Кто ? ", "life/kto_vi"))
            list.add(LifeModel("Люди любят откровения.", "life/lyudo_otkroveniya"))
            list.add(LifeModel("Пох*исты живут счастливо", "life/poxuisti_jivut_krasivo"))
            list.add(LifeModel("Книги", "life/knigi"))
            list.add(LifeModel("О саморазвитии", "life/samorazvitiye"))
            list.add(LifeModel("О лженауках", "life/lgenauka"))
            list.add(LifeModel("Манеры", "life/maneri"))
            list.add(LifeModel("Бездельник", "life/bezdelniki"))
            list.add(LifeModel("Плохо делать поспешные выводы", "life/pospeshniye_vivodi"))
            list.add(LifeModel("Как развить в себе харизму?", "life/xarizma"))
            list.add(LifeModel("О медицине", "life/medicina"))
            list.add(LifeModel("Неумение отстоять свою точку зрения.", "life/tocka_zraniya"))
            list.add(LifeModel("Безответственные", "life/bezotstveniye"))


            CoroutineScope(Dispatchers.IO).launch {
                list.forEach { life ->
                    dao.insertLife(life)
                }
            }
        }
    }
}