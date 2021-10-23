package pdf.reader.happiness.data.cache.data_source

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import pdf.reader.happiness.core.MusicCloudResult
import pdf.reader.happiness.core.TokenCloudResult
import pdf.reader.happiness.data.cache.models.MusicCloudModel
import pdf.reader.happiness.data.cloud.data_insert.TokenIdGenerator
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

interface MusicPathDataSource {

    suspend fun fetMusics():MusicCloudResult
    suspend fun addMusic(musicCloudModel: MusicCloudModel)

    class Base(private val fireStore: FirebaseFirestore) : MusicPathDataSource {

        override suspend fun fetMusics() = suspendCoroutine<MusicCloudResult>{ continuation->
            fireStore.collection("music_url")
                .get()
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        val music = it.result.toObjects(MusicCloudModel::class.java)
                        Log.d("pos3","DURATION $music")

                        if (music.isEmpty()){
                            continuation.resume(MusicCloudResult.Fail("Error"))
                        }else {
                            continuation.resume(MusicCloudResult.Success(music))
                        }
//                        }else {
//                            //continuation.resume(MusicCloudResult.Fail("Error"))
//                        }
                       // music.clear()
                    }
                }
        }

        override suspend fun addMusic(musicCloudModel: MusicCloudModel) {
            val generator = TokenIdGenerator()
            fireStore.document("music_url/${generator.getGeneratedId()}").set(musicCloudModel)
        }
    }
}