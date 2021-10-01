package pdf.reader.happiness.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import pdf.reader.happiness.databinding.ActivityReadingBinding
import com.like.LikeButton
import com.like.OnLikeListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.component.inject
import pdf.reader.happiness.data.models.InfoModel
import pdf.reader.happiness.tools.AssetReader
import pdf.reader.happiness.tools.ReadingWarningDialog
import pdf.reader.happiness.vm.ReadingViewModel


@KoinApiExtension
class ReadingActivity : AppCompatActivity(), KoinComponent,
    AssetReader.ExitCallBack,ReadingWarningDialog.DialogCallBack {

    private lateinit var binding: ActivityReadingBinding
    private val assetReader: AssetReader by inject()
    private val viewModel: ReadingViewModel = get()
    private val presenter:ReadingActivityPresenter by inject()
    private lateinit var intent: InfoModel
    private val warningDialog = ReadingWarningDialog.Base(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReadingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        intent = getIntent().getSerializableExtra("data") as InfoModel

        binding.toolbar.titleTv.text = intent.title
        binding.toolbar.backBtn.setOnClickListener {
            if (!intent.finished) {
                warningDialog.show(this)
            }else{
                finish()
            }
        }
        binding.toolbar.likeBtn.isLiked = intent.favorite

        presenter.checkFontState(binding.bodyTv)
        presenter.checkThemeState()

        CoroutineScope(Dispatchers.Main).launch {
            binding.bodyTv.text = assetReader.read(intent.body, this@ReadingActivity)
            viewModel.updateOpened(intent.body, true)

        }

        binding.toolbar.likeBtn.setOnLikeListener(object : OnLikeListener {
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

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        binding.toolbar.likeBtn.isLiked = savedInstanceState.getBoolean("like")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("like", binding.toolbar.likeBtn.isLiked)
    }

    override fun onBackPressed() {
        if (!intent.finished) {
            warningDialog.show(this)
        }else {
            finish()
        }
    }

    override fun exitCommand() { finish() }
    override fun exitCallBack() { finish() }

    override fun updateCallBack(state: Boolean) {
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.updateFinishedState(intent.body, state)
            finish()
        }
    }

}