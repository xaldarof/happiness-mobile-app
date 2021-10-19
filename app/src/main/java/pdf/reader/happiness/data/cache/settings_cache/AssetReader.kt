package pdf.reader.happiness.data.cache.settings_cache

import android.content.Context
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.FileNotFoundException
import java.lang.Exception

interface AssetReader {

    suspend fun read(path: String,exitCallBack: ExitCallBack): String

    class Base(private val context: Context) : AssetReader {
        override suspend fun read(path: String,exitCallBack: ExitCallBack): String {
            var body = ""
            try {
                withContext(Dispatchers.IO) {
                    body = context.assets.open(path).bufferedReader(Charsets.UTF_8)
                        .run {
                            readText()
                        }
                    return@withContext body
                }
            } catch (e: FileNotFoundException) {
                Toast.makeText(context, "Кэш приложения поврежден пожалуйста сбросьте  настройки.", Toast.LENGTH_LONG).show()
                exitCallBack.exitCommand(e)
            }
            return body
        }
    }
    interface ExitCallBack{
        fun exitCommand(message:Exception)
    }
}
