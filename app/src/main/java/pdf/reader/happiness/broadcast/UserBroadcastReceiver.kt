package pdf.reader.happiness.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import pdf.reader.happiness.R
import pdf.reader.happiness.tools.vibrateNormal

class UserBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        val state: Int = p1!!.getIntExtra("state", -1)
        if (state == 1) {
            Toast.makeText(p0, R.string.headphones_connected, Toast.LENGTH_LONG).show()
            p0?.vibrateNormal()
        }
    }
}