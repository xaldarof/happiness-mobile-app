package pdf.reader.happiness.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import pdf.reader.happiness.databinding.ActivityFundBinding

class FundActivity : AppCompatActivity() {

    private lateinit var binding:ActivityFundBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFundBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}