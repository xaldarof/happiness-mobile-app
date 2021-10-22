package pdf.reader.happiness.data.cache.data_source

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import pdf.reader.happiness.data.cache.models.MusicCloudModel
import pdf.reader.happiness.data.cloud.data_insert.TokenIdGenerator

interface MusicPathDataSource {

    suspend fun fetMusics(callBack: CallBack)
    suspend fun addMusic(musicCloudModel: MusicCloudModel)

    class Base(private val fireStore: FirebaseFirestore) : MusicPathDataSource {

        override suspend fun fetMusics(callBack: CallBack) {
            callBack.onLoad()
            fireStore.collection("music_url")
                .get()
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        val music = it.result.toObjects(MusicCloudModel::class.java)
                        callBack.onSuccess(music)
                        if (music.isEmpty()){
                            callBack.onFail()
                        }
                        music.clear()
                    }
                }
        }

        override suspend fun addMusic(musicCloudModel: MusicCloudModel) {
            val generator = TokenIdGenerator()
            fireStore.document("music_url/${generator.getGeneratedId()}").set(musicCloudModel)
        }
    }

    interface CallBack {
        fun onSuccess(list: List<MusicCloudModel>)
        fun onFail()
        fun onLoad()
    }
}