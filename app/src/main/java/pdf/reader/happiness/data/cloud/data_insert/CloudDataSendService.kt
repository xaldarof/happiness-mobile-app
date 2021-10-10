package pdf.reader.happiness.data.cloud.data_insert

import com.google.firebase.database.DatabaseReference
import pdf.reader.happiness.data.cloud.models.InfoCloudModel

interface CloudDataSendService {

    suspend fun sendData(cloudModel: InfoCloudModel)

    class Base(private val databaseReference: DatabaseReference): CloudDataSendService {
        override suspend fun sendData(cloudModel: InfoCloudModel) {
            databaseReference.child(cloudModel.title).setValue(cloudModel)
        }
    }
}