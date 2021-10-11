package pdf.reader.happiness.presentation

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import pdf.reader.happiness.R
import pdf.reader.happiness.databinding.ActivityDataImportingBinding
import pdf.reader.happiness.presentation.fragments.ShareFragment
import pdf.reader.happiness.tools.Animator
import pdf.reader.happiness.tools.ImportInfoDialog
import pdf.reader.happiness.vm.ImportingActivityViewModel
import render.animations.Attention
import render.animations.Render


@KoinApiExtension
class DataImportingActivity : AppCompatActivity(), KoinComponent,
    ImportingActivityViewModel.CallBack {

    private lateinit var binding: ActivityDataImportingBinding
    private val viewModel: ImportingActivityViewModel = get()

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
            binding.progressView.visibility = View.VISIBLE
            binding.start.isEnabled = false

            binding.cloudImg.setColorFilter(Color.BLACK)
            Animator(this).animation(binding.cloudImg,5000)
            binding.cloudImg.animate().rotation(360f).setDuration(10000).start()

            CoroutineScope(Dispatchers.IO).launch {
                importData()
            }
        }
    }

    private suspend fun importData() {
        viewModel.invoke(this@DataImportingActivity)
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