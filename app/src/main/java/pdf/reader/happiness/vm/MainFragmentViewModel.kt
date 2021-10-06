package pdf.reader.happiness.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import pdf.reader.happiness.data.core.DataRepository

class MainFragmentViewModel(private val dataRepository: DataRepository): ViewModel() {

    suspend fun fetchSuccess() = dataRepository.fetchSuccess().asLiveData()

    suspend fun fetchLife() = dataRepository.fetchLife().asLiveData()

    suspend fun fetchLove() = dataRepository.fetchLove().asLiveData()

    suspend fun fetchHappy() = dataRepository.fetchHappy().asLiveData()

    suspend fun fetchAll() = dataRepository.fetchAllTypes().asLiveData()

}