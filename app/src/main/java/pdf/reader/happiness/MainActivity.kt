package pdf.reader.happiness

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import pdf.reader.happiness.databinding.ActivityMainBinding
import pdf.reader.happiness.presentation.adapter.*
import pdf.reader.happiness.presentation.fragments.MainFragment
import pdf.reader.happiness.presentation.fragments.SearchFragment

@KoinApiExtension
class MainActivity : AppCompatActivity(),KoinComponent {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        FragmentController(this, listOf(MainFragment(),SearchFragment(),FavoritesFragment(),SettingsFragment()))

    }
}
