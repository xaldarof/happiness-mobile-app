package pdf.reader.happiness.data.cache

import androidx.lifecycle.asLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.junit.Assert.*
import org.junit.Test
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import pdf.reader.happiness.data.models.Type
import pdf.reader.happiness.data.dao.CoreDao

@KoinApiExtension
class LoveCacheDataSourceTest: KoinComponent {

    private val dao: CoreDao by inject()

    /**
     *  checked when actual size of data was 25
     */


    @Test
    fun check_is_db_returns_data(){
        CoroutineScope(Dispatchers.Main).launch {
            dao.fetchLove(Type.LOVE).asLiveData().observeForever {
                assertEquals(25,it.size)
            }
        }
    }

    @Test
    fun check_is_db_returns_only_life_type_data(){
        CoroutineScope(Dispatchers.Main).launch {
            dao.fetchLove(Type.LOVE).asLiveData().observeForever { it ->
                it.forEach {
                    assertEquals(Type.LOVE,it.type)

                    assertNotEquals(Type.HAPPY,it.type)
                    assertNotEquals(Type.LIFE,it.type)
                    assertNotEquals(Type.SUCCESS,it.type)
                }
            }
        }
    }
}