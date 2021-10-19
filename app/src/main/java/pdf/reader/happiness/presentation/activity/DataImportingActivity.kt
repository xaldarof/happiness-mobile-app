package pdf.reader.happiness.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.koin.core.component.KoinApiExtension
import pdf.reader.happiness.databinding.ActivityDataImportingBinding
import android.R
import pdf.reader.happiness.presentation.adapter.DataImportFragmentController
import pdf.reader.happiness.presentation.fragments.ExchangeFragment
import pdf.reader.happiness.presentation.fragments.ShareFragment

@KoinApiExtension
class DataImportingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDataImportingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDataImportingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        DataImportFragmentController(this, listOf(ExchangeFragment(),ShareFragment()))
    }
}