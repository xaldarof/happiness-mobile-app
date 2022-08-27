package pdf.reader.happiness.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get
import org.koin.core.component.get
import pdf.reader.happiness.core.UiState
import pdf.reader.happiness.databinding.LoginFragmentLayoutBinding
import pdf.reader.happiness.tools.disable
import pdf.reader.happiness.tools.enable
import pdf.reader.happiness.vm.LoginViewModel
import pdf.reader.happiness.vm.TokenViewModel

/**
 * @Author: Temur
 * @Date: 27/08/2022
 */

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: LoginFragmentLayoutBinding
    private val viewModel: LoginViewModel = get()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginFragmentLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginBtn.setOnClickListener {
            binding.loginBtn.disable()
            viewModel.login(binding.loginEdt.text.toString(), binding.paswordEdt.text.toString())
        }


        lifecycleScope.launch {
            viewModel.loginState.collectLatest {
                when (it) {
                    is UiState.Success<String> -> {
                        binding.loginBtn.enable()
                    }
                    is UiState.Fail -> {
                        binding.loginBtn.enable()

                    }
                    is UiState.ValidationError -> {
                        binding.loginBtn.enable()
                    }
                }
            }
        }
    }
}