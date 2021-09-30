package pdf.reader.happiness

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import pdf.reader.happiness.data.settings_cache.ThemeController
import pdf.reader.happiness.databinding.ActivityMainBinding
import pdf.reader.happiness.presentation.adapter.*
import pdf.reader.happiness.presentation.fragments.MainFragment
import pdf.reader.happiness.presentation.fragments.SearchFragment

@KoinApiExtension
class MainActivity : AppCompatActivity(),KoinComponent {

    private lateinit var binding: ActivityMainBinding
    private lateinit var themeController:ThemeController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        val sharedPreferences = getSharedPreferences("cache", MODE_PRIVATE)
        themeController = ThemeController.Base(sharedPreferences)

        if (themeController.isDarkThemeOn()){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
        FragmentController(this, listOf(MainFragment(),SearchFragment(),FavoritesFragment(),SettingsFragment()))


    }
}
