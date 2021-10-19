package pdf.reader.happiness.tools

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import pdf.reader.happiness.data.cache.settings_cache.AssetReader


class AssetReaderTest: AssetReader.ExitCallBack{

    private companion object {
        const val TEST_PATH = "happy/cicati100"
    }
    private lateinit var assertReader: AssetReader

    @Before
    fun setUp(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        assertReader = AssetReader.Base(context)
    }

    @Test
    fun check_is_assertReader_works(): Unit = runBlocking {
        val actual = assertReader.read(TEST_PATH,this@AssetReaderTest)
        assertNotEquals("",actual)
    }

    override fun exitCommand(message:Exception) {

    }
}