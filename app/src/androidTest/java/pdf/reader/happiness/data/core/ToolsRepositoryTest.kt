package pdf.reader.happiness.data.core

import androidx.lifecycle.asLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.junit.Assert.*
import org.junit.Test
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@KoinApiExtension
class ToolsRepositoryTest: KoinComponent {

    private val toolsRepository:ToolsRepository by inject()

    @Test
    fun check_is_repo_return_only_favorites() {
        CoroutineScope(Dispatchers.Main).launch {
            toolsRepository.fetchFavorites().asLiveData().observeForever { it ->
                it.forEach {
                    assertEquals(true,it.favorite)
                }
            }
        }
    }

    private val TEST_BODY = "happy/citati_o_scaste"

    @Test
    fun check_is_repo_updated_favorite_state() {

        CoroutineScope(Dispatchers.Main).launch {
            toolsRepository.updateFavoriteState(TEST_BODY,true)
            toolsRepository.fetchFavorites().asLiveData().observeForever { it ->
                assertTrue(it.map { it.body }.contains(TEST_BODY))
            }
        }
    }

}