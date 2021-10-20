package pdf.reader.happiness.core

import pdf.reader.happiness.data.cache.models.TokenModelDb
import pdf.reader.happiness.data.cloud.models.TokenCloudModel

//
//class CloudResult<out T>(val data: T?, val status: Status) {
//companion object {
//
//    fun <T> success(data: T, status: Status): CloudResult<T> {
//        return CloudResult(data, status)
//    }
//
//    fun <T> error(data: T?, status: Status): CloudResult<T> {
//        return CloudResult(data, status)
//    }
//}
//}

sealed class CloudResult{
    data class Success(val data:TokenCloudModel):CloudResult()
    data class Fail(val message:String):CloudResult()
}

enum class Status{
    ERROR,
    SUCCESS
}
