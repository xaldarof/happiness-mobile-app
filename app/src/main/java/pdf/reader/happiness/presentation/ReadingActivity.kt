package pdf.reader.happiness.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import pdf.reader.happiness.data.models.InfoModel
import pdf.reader.happiness.databinding.ActivityReadingBinding

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import com.like.LikeButton
import com.like.OnLikeListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.component.inject
import pdf.reader.happiness.R
import pdf.reader.happiness.tools.AssetReader
import pdf.reader.happiness.vm.ReadingViewModel
import java.io.FileNotFoundException


@KoinApiExtension
class ReadingActivity : AppCompatActivity(),KoinComponent,AssetReader.ExitCallBack {

    private lateinit var binding : ActivityReadingBinding
    private val assetReader:AssetReader by inject()
    private val viewModel:ReadingViewModel = get()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReadingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        val intent = intent.getSerializableExtra("data") as InfoModel

        binding.toolbar.likeBtn.isLiked = intent.favorite

        CoroutineScope(Dispatchers.Main).launch {
           binding.bodyTv.text = assetReader.read(intent.body,this@ReadingActivity)
        }

        binding.toolbar.titleTv.text = intent.title
        binding.toolbar.backBtn.setOnClickListener { finish() }

        binding.toolbar.likeBtn.setOnLikeListener(object :OnLikeListener{
            override fun liked(likeButton: LikeButton?) {
                CoroutineScope(Dispatchers.IO).launch {
                    viewModel.updateFavorite(intent.body, true)
                }
            }

            override fun unLiked(likeButton: LikeButton?) {
                CoroutineScope(Dispatchers.IO).launch {
                    viewModel.updateFavorite(intent.body, false)
                }
            }
        })
    }

    override fun exitCommand() {
        finish()
    }
}