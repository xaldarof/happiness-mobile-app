package pdf.reader.happiness.data.cloud.data_source

import com.google.firebase.firestore.FirebaseFirestore
import pdf.reader.happiness.core.TokenCloudResult
import pdf.reader.happiness.data.cloud.data_insert.TokenIdGenerator
import pdf.reader.happiness.data.cloud.models.TokenCloudModel
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

interface TokenCloudDataSource {

    suspend fun fetchTokenById(id: String): TokenCloudResult

    suspend fun createToken(tokenValue: Int)
    suspend fun createTokenByUser(tokenValue: Int, tokenId: String)

    suspend fun removeToken(tokenId: String): Boolean

    class Base(
        private val fireStore: FirebaseFirestore,
        private val tokenIdGenerator: TokenIdGenerator
    ) : TokenCloudDataSource {

        override suspend fun fetchTokenById(id: String) = suspendCoroutine<TokenCloudResult> { continuation ->
                var token: TokenCloudModel? = null

                fireStore.document("tokens/$id")
                    .get()
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            token = it.result.toObject(TokenCloudModel::class.java)
                            if (token!=null){
                            continuation.resume(TokenCloudResult.Success(token!!))

                            }
                            else {
                                continuation.resume(TokenCloudResult.Fail("Error"))
                            }
                        }
                    }
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