package pdf.reader.happiness.data.cloud.data_insert

import android.util.Log
import com.google.firebase.database.DatabaseReference
import pdf.reader.happiness.data.cloud.models.InfoCloudModel
import pdf.reader.happiness.tools.formatForDatabase

interface CloudDataSendService {

    suspend fun sendData(
        cloudModel: InfoCloudModel, onSuccess: () -> Unit,
        onFail: (String) -> Unit
    )

    class Base(private val databaseReference: DatabaseReference) : CloudDataSendService {
        override suspend fun sendData(
            cloudModel: InfoCloudModel, onSuccess: () -> Unit,
            onFail: (String) -> Unit
        ) {
            val formatted = cloudModel.title.formatForDatabase()

            databaseReference.child(formatted).setValue(cloudModel).addOnSuccessListener {
                onSuccess()
            }.addOnFailureListener {
                onFail("Что то пошло не так")
            }
        }
    }
}