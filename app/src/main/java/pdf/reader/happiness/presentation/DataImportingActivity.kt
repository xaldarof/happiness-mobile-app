package pdf.reader.happiness.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import pdf.reader.happiness.R
import pdf.reader.happiness.databinding.ActivityDataImportingBinding
import pdf.reader.happiness.presentation.fragments.ShareFragment
import pdf.reader.happiness.tools.ConnectionManager
import pdf.reader.happiness.tools.ImportInfoDialog
import pdf.reader.happiness.tools.animation
import pdf.reader.happiness.vm.ImportingActivityViewModel


@KoinApiExtension
class DataImportingActivity : AppCompatActivity(), KoinComponent,
    ImportingActivityViewModel.CallBack {

    private lateinit var binding: ActivityDataImportingBinding
    private val viewModel: ImportingActivityViewModel = get()
    private val connectionManager = ConnectionManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDataImportingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        binding.progressView.visibility = View.INVISIBLE

        binding.info.setOnClickListener {
            ImportInfoDialog.Base().show(this@DataImportingActivity)
        }
        binding.share.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.mainContainer,ShareFragment()).addToBackStack("").commit()
        }

        binding.start.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                importData()
            }
        }
    }

    private suspend fun importData() {
        if (connectionManager.isConnected()) {
            CoroutineScope(Dispatchers.Main).launch {
                binding.progressView.visibility = View.VISIBLE
                binding.start.isEnabled = false
                binding.cloudImg.animation()
            }
            viewModel.invoke(this@DataImportingActivity)

        }else {
            CoroutineScope(Dispatchers.Main).launch {
                Toast.makeText(this@DataImportingActivity, R.string.no_connection, Toast.LENGTH_SHORT).show()
                connectionManager.enableWifi()
            }
        }
    }

    override fun callback(count: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            binding.progressView.visibility = View.INVISIBLE
            binding.start.isEnabled = true
            Toast.makeText(
                this@DataImportingActivity, "Импортирован $count новых данных",
                Toast.LENGTH_SHORT
            ).show()
            finish()
        }
    }
}