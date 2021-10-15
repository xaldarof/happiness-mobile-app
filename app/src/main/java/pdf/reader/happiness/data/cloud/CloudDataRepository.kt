package pdf.reader.happiness.data.cloud

import kotlinx.coroutines.flow.Flow
import pdf.reader.happiness.data.cloud.models.InfoCloudModel

interface CloudDataRepository {

    suspend fun fetchCloudData():Flow<List<InfoCloudModel>>

    class Base(private val cloudDataSource: InfoCloudDataSource): CloudDataRepository {
        override suspend fun fetchCloudData(): Flow<List<InfoCloudModel>> {
            return cloudDataSource.fetchInfoAsFlow()
        }
    }
}