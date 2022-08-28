package pdf.reader.happiness.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import org.koin.android.ext.android.get
import pdf.reader.happiness.R
import pdf.reader.happiness.databinding.ActivityExchangeCoinRoomBinding
import pdf.reader.happiness.tools.disable
import pdf.reader.happiness.tools.enable
import pdf.reader.happiness.vm.ExchangeCoinViewModel

class ExchangeCoinRoomActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExchangeCoinRoomBinding
    private val viewModel: ExchangeCoinViewModel = get()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExchangeCoinRoomBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)

        binding.backBtn.setOnClickListener {
            finish()
        }

        binding.sendBtn.setOnClickListener {
            binding.sendBtn.disable()
            viewModel.sendTo(
                binding.usernameEdt.text.toString().trim(),
                onSuccess = {
                    finish()
                    binding.sendBtn.enable()
                },
                onFail = {
                    Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
                    binding.sendBtn.enable()
                },
                count = if (binding.countEdt.text.toString().trim()
                        .isEmpty()
                ) -1 else binding.countEdt.text.toString().trim().toInt()
            )
        }
    }
}