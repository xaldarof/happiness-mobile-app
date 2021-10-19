package pdf.reader.happiness.data.cache.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import pdf.reader.happiness.core.TokenModel
import java.io.Serializable

@Entity(tableName = "token_history")
data class TokenModelDb(
    val tokenValue: Int,
    val tokenDate: String,

    @PrimaryKey
    val tokenId: String
) :
    Serializable {

    fun mapToToken(): TokenModel {
        return TokenModel(tokenValue, tokenDate, tokenId)
    }
}