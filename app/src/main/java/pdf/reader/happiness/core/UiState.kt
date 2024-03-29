package pdf.reader.happiness.core

/**
 * @Author: Temur
 * @Date: 27/08/2022
 */

sealed class UiState<T> {
    data class Success<T>(val data: T? = null) : UiState<T>()
    data class Fail(val message: String? = null) : UiState<String>()
}