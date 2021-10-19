package pdf.reader.happiness.core

import pdf.reader.happiness.data.cache.models.TokenModelDb
import pdf.reader.happiness.data.cloud.models.TokenCloudModel
import java.io.Serializable

data class TokenModel(val tokenValue: Int, val tokenDate: String,val tokenId:String) : Serializable {

    fun mapToTokenCloud():TokenCloudModel {
        return TokenCloudModel(tokenValue, tokenDate,tokenId)
    }

    fun mapToTokenDb():TokenModelDb {
        return TokenModelDb(tokenValue, tokenDate,tokenId)
    }
}

