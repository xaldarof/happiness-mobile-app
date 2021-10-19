package pdf.reader.happiness.data.cache.core

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
class UserCoinRepositoryTest : KoinComponent {

    private val userCoinRepository: UserCoinRepository by inject()

    @Test
    fun check_is_user_coin_updates_after_pay() {
        val beforeTransaction = userCoinRepository.fetchUserCoinCount()
        userCoinRepository.payWithCoin(1)
        val afterTransaction = userCoinRepository.fetchUserCoinCount()

        assertTrue(beforeTransaction > afterTransaction)
    }

    @Test
    fun check_is_user_coin_updates_after_watching_ad(){
        val beforeTransaction = userCoinRepository.fetchUserCoinCount()
        userCoinRepository.updateUserCoinCount()
        val afterTransaction = userCoinRepository.fetchUserCoinCount()

        assertTrue(afterTransaction > beforeTransaction)

    }

    @Test
    fun check_is_user_coin_comes_from_db() {
        assertTrue(userCoinRepository.fetchUserCoinCount() >= 0)
    }

    @Test
    fun check_is_user_coin_comes_from_db_as_flow() {
        CoroutineScope(Dispatchers.Main).launch {
            userCoinRepository.fetchUserCoinCountAsFlow().asLiveData().observeForever {
                assertTrue(it >= 0)
            }
        }
    }
}