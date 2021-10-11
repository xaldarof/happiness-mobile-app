package pdf.reader.happiness.tools

import android.content.Context
import android.content.Intent
import android.net.Uri


fun Context.openPlayMarket(){
        startActivity(Intent(Intent.ACTION_VIEW,
            Uri.parse("https://play.google.com/store/apps/details?id=pdf.reader.happiness&hl=ru&gl=US")))
}
