package pdf.reader.happiness.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import android.R
import android.widget.AdapterView
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pdf.reader.happiness.data.cache.models.Type
import pdf.reader.happiness.data.cloud.data_insert.CloudDataSendService
import pdf.reader.happiness.data.cloud.models.InfoCloudModel
import pdf.reader.happiness.databinding.FragmentShareBinding
import pdf.reader.happiness.tools.TypeLocator


@KoinApiExtension
class ShareFragment : Fragment(), KoinComponent {

    private lateinit var binding: FragmentShareBinding
    private val typeLocator = TypeLocator()
    private val sendService: CloudDataSendService by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShareBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.backBtn.setOnClickListener { requireActivity().supportFragmentManager.popBackStack() }
        var type = ""
        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.simple_spinner_dropdown_item,
            arrayListOf("СЧАСТЬЕ", "ЛЮБОВЬ","УСПЕХ","ЖИЗНЬ"))

        binding.spinner.adapter = adapter

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                type = p0?.getItemAtPosition(p2).toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

        binding.sendBtn.setOnClickListener {
            val title = binding.titleEditText.text.toString()
            val body = binding.bodyEditText.text.toString()
            if (title.isNotEmpty() && body.isNotEmpty()) {
                sendData(type)
                requireActivity().supportFragmentManager.popBackStack()
                Toast.makeText(requireContext(), "Успешно отправлено на проверку",
                    Toast.LENGTH_SHORT).show()
            }
        }


    }
    private fun sendData(type:String){
        CoroutineScope(Dispatchers.Main).launch {
            sendService.sendData(
                InfoCloudModel(
                    binding.titleEditText.text.toString(),
                    binding.bodyEditText.text.toString(),
                    type = typeLocator.locate(type), dataType = Type.CLOUD))
        }
    }
}