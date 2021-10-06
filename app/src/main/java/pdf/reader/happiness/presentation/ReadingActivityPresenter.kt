package pdf.reader.happiness.presentation

import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pdf.reader.happiness.data.settings_cache.FontController
import pdf.reader.happiness.data.settings_cache.ThemeController
import pdf.reader.happiness.tools.TypeFaceController

class ReadingActivityPresenter(private val font: FontController,private val theme: ThemeController){

    fun checkFontState(textView: TextView){
        if (font.isBoldFont()) {
            TypeFaceController.Base(textView.context).changeToBold(textView)
        } else {
            TypeFaceController.Base(textView.context).changeToDef(textView)
        }
    }

    fun checkThemeState(){
        if (theme.isDarkThemeOn()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
}