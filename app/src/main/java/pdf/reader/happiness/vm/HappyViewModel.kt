package pdf.reader.happiness.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import pdf.reader.happiness.data.core.DataRepository
import pdf.reader.happiness.data.models.InfoModel

class HappyViewModel(private val dataRepository: DataRepository): ViewModel() {

    suspend fun fetchHappy() = dataRepository.fetchHappy().asLiveData()

}