package pdf.reader.happiness.data.cache.data_source

import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@KoinApiExtension
class StatisticInfoProviderTest : KoinComponent {

    private val statistic: StatisticInfoProvider by inject()

    /**
     * Test when user read every chapter data more than 0
     */


    @Test
    fun test_is_all_finished_equals_to_data_finished_count() = runBlocking {
        val actual = statistic.fetchFinishedCount()
        val expected = statistic.fetchHappyFinishedCount()+
                statistic.fetchLifeFinishedCount()+
                statistic.fetchSuccessFinishedCount()+
                statistic.fetchLoveFinishedCount()

        assertEquals(expected,actual)
    }
    @Test
    fun test_is_provides_happy_finished_count() {
        val actual = statistic.fetchHappyFinishedCount()
        assertTrue(actual > 0)
    }

    @Test
    fun test_is_provides_life_finished_count() {
        val actual = statistic.fetchLifeFinishedCount()
        assertTrue(actual > 0)
    }

    @Test
    fun test_is_provides_success_finished_count() {
        val actual = statistic.fetchSuccessFinishedCount()
        assertTrue(actual > 0)
    }

    @Test
    fun test_is_provides_love_finished_count() {
        val actual = statistic.fetchLoveFinishedCount()
        assertTrue(actual > 0)
    }


    @Test
    fun test_is_provides_wasted_time_happy() = runBlocking {
        val actual = statistic.fetchHappyWastedTime()
        assertTrue(actual > 0)
    }

    @Test
    fun test_is_provides_wasted_time_life() = runBlocking {
        val actual = statistic.fetchLifeWastedTime()
        assertTrue(actual > 0)
    }

    @Test
    fun test_is_provides_wasted_time_love() = runBlocking {
        val actual = statistic.fetchLoveWastedTime()
        assertTrue(actual > 0)
    }

    @Test
    fun test_is_provides_wasted_time_success() = runBlocking {
        val actual = statistic.fetchSuccessWastedTime()
        assertTrue(actual > 0)
    }

}