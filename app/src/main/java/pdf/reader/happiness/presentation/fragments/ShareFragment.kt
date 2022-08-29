package pdf.reader.happiness.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import android.widget.AdapterView
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.get
import androidx.viewpager2.widget.ViewPager2
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper
import pdf.reader.happiness.R
import pdf.reader.happiness.core.Name
import pdf.reader.happiness.data.cache.models.Type
import pdf.reader.happiness.data.cloud.models.InfoCloudModel
import pdf.reader.happiness.databinding.FragmentShareBinding
import pdf.reader.happiness.tools.*
import pdf.reader.happiness.vm.ShareViewModel


@KoinApiExtension
class ShareFragment : Fragment(), KoinComponent, ImportInfoDialog.CallBack {

    private lateinit var binding: FragmentShareBinding
    private val viewModel: ShareViewModel = get()
    private val typeLocator = TypeLocator()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShareBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        OverScrollDecoratorHelper.setUpOverScroll(binding.scroll)

        val viewPager2 = requireActivity().findViewById<ViewPager2>(R.id.pagerImportActivity)
        binding.backBtn.setOnClickListener { viewPager2.currentItem-- }

        binding.clearTextBtn.setOnClickListener {
            ImportInfoDialog.Base().showClearText(requireContext(), this)
        }

        val adapter = ArrayAdapter(
            requireContext(), android.R.layout.simple_spinner_dropdown_item,
            arrayListOf(Name.HAPPY, Name.LOVE, Name.SUCCESS, Name.LIFE)
        )

        var type = ""
        binding.spinner.adapter = adapter

        binding.spinner.onItemSelectedListener = SpinnerClickCallBack(object : CallBackSpinner {
            override fun onSelect(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                type = p0?.getItemAtPosition(p2).toString()
            }
        })

        binding.sendBtn.setOnClickListener { checkData(type) }
    }

    private fun checkData(type: String) {
        val title = binding.titleEditText.text.toString()
        val body = binding.bodyEditText.text.toString()

        if (title.trim().isNotEmpty() && body.trim().isNotEmpty()) {
            if (viewModel.fetchUserCoinCount() >= 1) {
                sendData(type, title, body)
                ImportInfoDialog.Base().showInfoAboutPublish(requireContext(), this)
            } else {
                Toast.makeText(
                    requireContext(), "У вас недостаточно монет для отправки данных",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else {
            if (title.isEmpty()) binding.titleEditText.errorAnimation()
            if (body.isEmpty()) binding.bodyEditText.errorAnimation()
        }
    }

    private fun sendData(type: String, title: String, body: String) {
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.sendDataToFirebase(
                InfoCloudModel(
                    title,
                    body,
                    type = typeLocator.locate(type),
                    dataType = Type.CLOUD
                ), onFail = {
                    Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                }, onSuccess = {
                    Toast.makeText(requireContext(), "Успешно отправлено !", Toast.LENGTH_SHORT)
                        .show()
                    binding.titleEditText.text = null
                    binding.bodyEditText.text = null
                }
            )
        }
    }

    override fun onClickClear() {
        binding.bodyEditText.setText("")
        binding.titleEditText.setText("")
    }
}