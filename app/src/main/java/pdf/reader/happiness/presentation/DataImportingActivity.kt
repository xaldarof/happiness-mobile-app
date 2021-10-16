package pdf.reader.happiness.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.component.inject
import pdf.reader.happiness.R
import pdf.reader.happiness.data.cache.core.UserCoinRepository
import pdf.reader.happiness.databinding.ActivityDataImportingBinding
import pdf.reader.happiness.presentation.fragments.ShareFragment
import pdf.reader.happiness.tools.*
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
            val commonPrice = binding.priceTv.text.toString().toInt()

            if (viewModel.fetchUserCoinCount()>=commonPrice) {
                CoroutineScope(Dispatchers.Main).launch {
                    importData(binding.editTextCount.text.toString().toInt())
                }

            }else {
                Toast.makeText(this, "У вас не хватает количество монет", Toast.LENGTH_SHORT).show()
            }
        }


        binding.editTextCount.addTextChangedListener(MyTextWatcher(object :CallBack{
            override fun onChange(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.priceTv.text = if (binding.editTextCount.text.toString().isNotEmpty())
                        (binding.editTextCount.text.toString().toLong()*3).toString() else "0"
            }
        }))

        viewModel.fetchUserCoinAsLiveData().observe(this, {
            binding.balanceTv.text = it.toString()
        })

    }

    private suspend fun importData(userEnteredInfoCount: Int) {
        if (connectionManager.isConnected()) {
            CoroutineScope(Dispatchers.Main).launch {
                binding.progressView.visibility = View.VISIBLE
                binding.start.isEnabled = false
            }

            viewModel.invoke(this@DataImportingActivity,userEnteredInfoCount)

        }
        else {
            CoroutineScope(Dispatchers.Main).launch {
                Toast.makeText(this@DataImportingActivity, R.string.no_connection,
                    Toast.LENGTH_SHORT).show()
                connectionManager.enableWifi()
            }
        }
    }

    private fun onFinishExchange(){
        binding.progressView.visibility = View.INVISIBLE
        binding.start.isEnabled = true

    }

    override fun onSuccessCallBack(count: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            onFinishExchange()
            Toast.makeText(this@DataImportingActivity, "Обмен успешно завершен. Вы получили $count новых данных",
                Toast.LENGTH_SHORT).show()
            viewModel.payWithCoin(binding.priceTv.text.toString().toInt())

        }
    }

    override fun onOutOfBounds(count: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            Toast.makeText(this@DataImportingActivity,
                "Вы просите слишком большое количество данных, максимум: $count",
                Toast.LENGTH_SHORT
            ).show()
            onFinishExchange()
        }
    }
}