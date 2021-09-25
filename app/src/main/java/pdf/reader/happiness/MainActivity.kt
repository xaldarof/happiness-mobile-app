package pdf.reader.happiness

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        navController.navigate(R.id.mainFragment)

    }
}