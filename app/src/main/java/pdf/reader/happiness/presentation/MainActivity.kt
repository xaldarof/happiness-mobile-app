package pdf.reader.happiness.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.component.inject
import pdf.reader.happiness.data.settings_cache.BadgeController
import pdf.reader.happiness.data.settings_cache.ThemeController
import pdf.reader.happiness.databinding.ActivityMainBinding
import pdf.reader.happiness.di.presenters
import pdf.reader.happiness.presentation.adapter.*
import pdf.reader.happiness.presentation.fragments.AchievementsFragment
import pdf.reader.happiness.presentation.fragments.MainFragment
import pdf.reader.happiness.presentation.fragments.SearchFragment
import pdf.reader.happiness.vm.MainActivityViewModel

@KoinApiExtension
class MainActivity : AppCompatActivity(), KoinComponent {

    private lateinit var binding: ActivityMainBinding
    private lateinit var themeController: ThemeController
    private val badgeController:BadgeController by inject()
    private val viewModel:MainActivityViewModel = get()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        val sharedPreferences = getSharedPreferences("cache", MODE_PRIVATE)
        themeController = ThemeController.Base(sharedPreferences)

        if (themeController.isDarkThemeOn()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
        FragmentController(
            this, listOf(
                MainFragment(), SearchFragment(),
                FavoritesFragment(),
                AchievementsFragment(),
                SettingsFragment()),badgeController)

        CoroutineScope(Dispatchers.IO).launch {
            viewModel.updateWastedTime()
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.startWastingTime()
    }
}
