package pdf.reader.happiness.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import pdf.reader.happiness.data.models.InformationModel
import pdf.reader.happiness.databinding.ActivityReadingBinding
import java.io.*
import java.nio.charset.Charset
import java.nio.charset.CharsetDecoder

class ReadingActivity : AppCompatActivity() {

    private lateinit var binding : ActivityReadingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReadingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val intent = intent.getSerializableExtra("data") as InformationModel

        val body = application.assets.open(intent.body).bufferedReader(Charsets.UTF_8).use{
            it.readText()
        }
        binding.bodyTv.text = body

    }
}