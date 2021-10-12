package pdf.reader.happiness.tools

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService  : FirebaseMessagingService() {
    override fun onNewToken(p0: String) {
        Log.d("pos3","NEW TOKEN = $p0")
    }
}