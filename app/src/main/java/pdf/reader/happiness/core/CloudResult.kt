package pdf.reader.happiness.core


class CloudResult<out T>(val data: T?, val status: Status) {
companion object {

    fun <T> success(data: T, status: Status): CloudResult<T> {
        return CloudResult(data, status)
    }

    fun <T> error(data: T?, status: Status): CloudResult<T> {
        return CloudResult(data, status)
    }
}
}

enum class Status{
    ERROR,
    SUCCESS
}
