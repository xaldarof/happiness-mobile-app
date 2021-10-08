package pdf.reader.happiness.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import pdf.reader.happiness.data.core.ChaptersRepository
import pdf.reader.happiness.data.core.DataRepository

class MainFragmentViewModel(
    private val chaptersRepository: ChaptersRepository,
    private val dataRepository: DataRepository) : ViewModel() {


    suspend fun fetchChapters() = chaptersRepository.fetchChapters().asLiveData()

    suspend fun fetchAll()= dataRepository.fetchAllTypes().asLiveData()


}