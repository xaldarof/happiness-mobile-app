package pdf.reader.happiness.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import pdf.reader.happiness.data.core.DataRepository

class SuccessViewModel(private val repository: DataRepository): ViewModel() {

    suspend fun fetchSuccess() = repository.fetchSuccess().asLiveData()
}