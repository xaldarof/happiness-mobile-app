package pdf.reader.happiness.tools

import android.app.Activity
import androidx.core.app.ShareCompat


fun Activity.shareApp() {
    ShareCompat.IntentBuilder.from(this)
        .setType("text/plain")
        .setChooserTitle("Выберите...")
        .setText("http://play.google.com/store/apps/details?id=" + this.packageName)
        .startChooser();
}
