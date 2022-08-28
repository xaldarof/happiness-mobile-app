package pdf.reader.happiness.presentation.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get
import org.koin.core.component.KoinApiExtension
import pdf.reader.happiness.core.UiState
import pdf.reader.happiness.databinding.ActivityRegisterBinding
import pdf.reader.happiness.tools.disable
import pdf.reader.happiness.tools.enable
import pdf.reader.happiness.vm.LoginViewModel
import pdf.reader.happiness.vm.RegisterViewModel

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private val viewModel: RegisterViewModel = get()

    @OptIn(KoinApiExtension::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)

        binding.registerBtn.setOnClickListener {
            binding.registerBtn.disable()
            viewModel.register(binding.loginEdt.text.toString().trim(), binding.paswordEdt.text.toString().trim())
        }

        binding.backBtn.setOnClickListener {
            finish()
        }


        lifecycleScope.launch {
            viewModel.loginState.collectLatest {
                when (it) {
                    is UiState.Success<String> -> {
                        binding.registerBtn.enable()
                        this@RegisterActivity.startActivity(
                            Intent(
                                this@RegisterActivity,
                                MainActivity::class.java
                            )
                        )
                        finish()
                    }
                    is UiState.Fail -> {
                        Toast.makeText(
                            this@RegisterActivity,
                            it.message,
                            Toast.LENGTH_SHORT
                        ).show()
                        binding.registerBtn.enable()

                    }
                }
            }
        }
    }
}