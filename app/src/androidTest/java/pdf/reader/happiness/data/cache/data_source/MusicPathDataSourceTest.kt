package pdf.reader.happiness.data.cache.data_source

import org.junit.Assert.*
import org.junit.Test
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

@KoinApiExtension
class MusicPathDataSourceTest:KoinComponent {


    private val musicPathDataSource:MusicPathDataSource by inject()

    @Test
    fun check_is_provides_path(){
        assertTrue(musicPathDataSource.fetRandomPath().isNotEmpty())
    }
}