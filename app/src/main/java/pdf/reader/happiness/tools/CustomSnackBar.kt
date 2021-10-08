package pdf.reader.happiness.tools

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Vibrator
import android.view.View
import android.widget.TextView
import com.google.android.material.snackbar.BaseTransientBottomBar.ANIMATION_MODE_FADE
import com.google.android.material.snackbar.Snackbar
import pdf.reader.happiness.*
import com.google.android.material.snackbar.Snackbar.SnackbarLayout
import java.util.logging.Handler
import androidx.test.core.app.ApplicationProvider.getApplicationContext

import android.media.AudioManager
import androidx.test.core.app.ApplicationProvider


interface CustomSnackBar {
    fun show(context: Context, view: View, message: String)


    class Base : CustomSnackBar {
        override fun show(context: Context, view: View, message: String) {
            val activity = context as Activity

            val snackbar = Snackbar.make(view, "", Snackbar.LENGTH_LONG)
            val layout = snackbar.view as SnackbarLayout

            val snackView: View = activity.layoutInflater.inflate(R.layout.snackbar_style, null)
            snackView.setBackgroundColor(Color.TRANSPARENT)

            val achievementInfo = snackView.findViewById<TextView>(R.id.tvInfoAchievment)
                //achievementInfo.text = message

            layout.setPadding(0, 0, 0, 0)
            layout.addView(snackView, 0)
            vibrate(context)

            snackbar.show()

        }

        private fun vibrate(context: Context) {
            val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            val pattern = longArrayOf(500,500)
            vibrator.vibrate(pattern, -1)
        }
    }

}