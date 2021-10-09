package pdf.reader.happiness.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import pdf.reader.happiness.databinding.ActivityDataImportingBinding
import pdf.reader.happiness.tools.ImportInfoDialog
import pdf.reader.happiness.vm.ImportingActivityViewModel


@KoinApiExtension
class DataImportingActivity : AppCompatActivity(),KoinComponent {

    private lateinit var binding:ActivityDataImportingBinding
    private val viewModel:ImportingActivityViewModel = get()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDataImportingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.info.setOnClickListener {
            ImportInfoDialog.Base().show(this@DataImportingActivity)
        }

        binding.start.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                viewModel.invoke()
            }
        }
    }
}