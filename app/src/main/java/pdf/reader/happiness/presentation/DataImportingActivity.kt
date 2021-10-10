package pdf.reader.happiness.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import pdf.reader.happiness.databinding.ActivityDataImportingBinding
import pdf.reader.happiness.tools.ImportInfoDialog
import pdf.reader.happiness.vm.ImportingActivityViewModel
import render.animations.Attention


@KoinApiExtension
class DataImportingActivity : AppCompatActivity(),KoinComponent,ImportingActivityViewModel.CallBack {

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
                importData()
            }
        }
    }
    private suspend fun importData(){
        viewModel.invoke(this@DataImportingActivity)
    }

    override fun callback(count: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            Toast.makeText(this@DataImportingActivity, "Импортирован $count новых данных", Toast.LENGTH_SHORT).show()
        }
    }
}