package pdf.reader.happiness.data.cloud.models

import pdf.reader.happiness.core.TokenModel
import java.io.Serializable

data class TokenCloudModel(
    val tokenValue: Int = 0,
    val tokenDate: String = "",
    val tokenId: String = ""
) : Serializable {

    fun mapToTokenModel(): TokenModel {
        return TokenModel(tokenValue, tokenDate, tokenId)
    }
}