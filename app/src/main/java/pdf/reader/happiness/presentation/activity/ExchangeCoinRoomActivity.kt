package pdf.reader.happiness.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import pdf.reader.happiness.R
import pdf.reader.happiness.databinding.ActivityExchangeCoinRoomBinding

class ExchangeCoinRoomActivity : AppCompatActivity() {

    private lateinit var binding:ActivityExchangeCoinRoomBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExchangeCoinRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}