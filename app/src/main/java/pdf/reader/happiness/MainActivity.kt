package pdf.reader.happiness

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import pdf.reader.happiness.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        navController.navigate(R.id.mainFragment)

        binding.bottomBar.setOnItemSelectedListener {
            if (it == 0) {
                navController.navigate(R.id.mainFragment)
            }
            if (it == 1) {
                navController.navigate(R.id.searchFragment)
            }
            if (it == 2) {
                navController.navigate(R.id.favoritesFragment)
            }
            if (it ==3){
                navController.navigate(R.id.settingsFragment)
            }
        }
    }
}
