package pdf.reader.happiness.data.cache.core

import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@KoinApiExtension
class StatisticRepositoryTest : KoinComponent {

    private val repository: StatisticRepository by inject()

    //Check when user entered more than 0 times to chapter

    @Test
    fun check_is_happy_wasted_time_changes(): Unit = runBlocking {
        val actual = repository.fetchHappyWastedTime()
        assertTrue(actual > 0)
    }

    @Test
    fun check_is_love_wasted_time_changes(): Unit = runBlocking {
        val actual = repository.fetchLoveWastedTime()
        assertTrue(actual > 0)
    }

    @Test
    fun check_is_life_wasted_time_changes(): Unit = runBlocking {
        val actual = repository.fetchLifeWastedTime()
        assertTrue(actual > 0)
    }

    @Test
    fun check_is_success_wasted_time_changes(): Unit = runBlocking {
        val actual = repository.fetchSuccessWastedTime()
        assertTrue(actual > 0)
    }

}