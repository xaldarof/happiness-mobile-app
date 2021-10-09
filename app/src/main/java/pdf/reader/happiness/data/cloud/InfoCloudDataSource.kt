package pdf.reader.happiness.data.cloud

import android.util.Log
import com.google.firebase.database.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import pdf.reader.happiness.data.cache.models.Type
import pdf.reader.happiness.data.cloud.models.InfoCloudModel

interface InfoCloudDataSource {

    suspend fun fetchInfo(): Flow<List<InfoCloudModel>>

    class Base(private val databaseReference: DatabaseReference) : InfoCloudDataSource {

        private val cloudInfoList = ArrayList<InfoCloudModel>()

        override suspend fun fetchInfo(): Flow<List<InfoCloudModel>> {
            val list = ArrayList<InfoCloudModel>()
            databaseReference.push().setValue(InfoCloudModel("TEST CLOUD DATA","CLOUD BODY",type=Type.SUCCESS))

            databaseReference.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(p0: DataSnapshot) {
                    val child = p0.children

                    child.forEach {
                        val value = it.getValue(InfoCloudModel::class.java)
                        value?.let { model ->
                            list.add(model)
                            Log.d("pos2","CLOUD = $model")
                        }
                    }
                    cloudInfoList.addAll(list)
                }

                override fun onCancelled(p0: DatabaseError) {
                }
            })
            Log.d("pos2","LIST = $list")
            return flow { emit(cloudInfoList)
                cloudInfoList.clear()
            }
        }
    }
}