package pdf.reader.happiness.data.cloud.data_source

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.delay
import pdf.reader.happiness.core.CloudResult
import pdf.reader.happiness.core.Status
import pdf.reader.happiness.data.cloud.data_insert.TokenIdGenerator
import pdf.reader.happiness.data.cloud.models.TokenCloudModel

interface TokenCloudDataSource {

    suspend fun fetchTokenById(id: String): CloudResult

    suspend fun createToken(tokenValue: Int)
    suspend fun createTokenByUser(tokenValue: Int, tokenId: String)

    suspend fun removeToken(tokenId: String): Boolean

    class Base(
        private val fireStore: FirebaseFirestore,
        private val tokenIdGenerator: TokenIdGenerator
    ) : TokenCloudDataSource {

        override suspend fun fetchTokenById(id: String): CloudResult {
            var token: TokenCloudModel? = null

            fireStore.document("tokens/$id")
                .get()
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        token = it.result.toObject(TokenCloudModel::class.java)
                    }
                }
            delay(5000)

            return if (token == null) CloudResult.Fail("NullPointerException")
            else CloudResult.Success(token!!)

        }

        override suspend fun createToken(tokenValue: Int) {
            val id = tokenIdGenerator.getGeneratedId()
            val token =
                TokenCloudModel(tokenValue, (System.currentTimeMillis() / 1000).toString(), id)

            fireStore.collection("tokens").document(id).set(token)
        }

        override suspend fun createTokenByUser(tokenValue: Int, tokenId: String) {
            val token =
                TokenCloudModel(tokenValue, (System.currentTimeMillis() / 1000).toString(), tokenId)

            fireStore.collection("tokens").document(tokenId).set(token)
        }

        override suspend fun removeToken(tokenId: String): Boolean {
            return fireStore.document("tokens/$tokenId")
                .delete().isSuccessful
        }
    }
}