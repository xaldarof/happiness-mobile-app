package pdf.reader.happiness.presentation.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import org.koin.android.ext.android.get
import org.koin.core.component.KoinApiExtension
import pdf.reader.happiness.R
import pdf.reader.happiness.presentation.fragments.LoginActivity
import pdf.reader.happiness.vm.LoginViewModel

class PresentationActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel = get()

    @KoinApiExtension
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_presentation)
        supportActionBar?.hide()
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)

        Handler(Looper.getMainLooper()).postDelayed({
            if(viewModel.isAuthorized) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }, 2500)

    }
}