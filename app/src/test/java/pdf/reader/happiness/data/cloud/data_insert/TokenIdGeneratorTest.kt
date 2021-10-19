package pdf.reader.happiness.data.cloud.data_insert

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class TokenIdGeneratorTest {

    @Test
    fun check_is_generates_id(){
        val tokenIdGenerator = TokenIdGenerator()
        assertEquals(tokenIdGenerator.getGeneratedId().length,12)
       // assertTrue(tokenIdGenerator.getGeneratedId().isNotEmpty())
    }
}