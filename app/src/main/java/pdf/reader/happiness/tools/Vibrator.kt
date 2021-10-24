package pdf.reader.happiness.tools

import android.content.Context
import android.os.Vibrator

fun Context.vibrate(){
    val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    val pattern = longArrayOf(1000,1000)
    vibrator.vibrate(pattern, -1)
}

fun Context.vibrateShort(){
    val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    val pattern = longArrayOf(250,250)
    vibrator.vibrate(pattern, -1)
}

fun Context.vibrateNormal(){
    val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    val pattern = longArrayOf(500,500)
    vibrator.vibrate(pattern, -1)
}