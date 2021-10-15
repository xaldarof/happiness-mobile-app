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
    private val userCoin:UserCoinRepository by inject()

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
            val userEnteredPrice = binding.priceTv.text.toString().toInt()

            if (userCoin.fetchUserCoinCount()>=userEnteredPrice) {
                userCoin.payWithCoin(userEnteredPrice)

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

    override fun onSuccessCallBack(count: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            binding.progressView.visibility = View.INVISIBLE
            binding.start.isEnabled = true
            Toast.makeText(this@DataImportingActivity, "Обмен успешно завершен. Вы получили $count новых данных",
                Toast.LENGTH_SHORT).show()

        }
    }
}