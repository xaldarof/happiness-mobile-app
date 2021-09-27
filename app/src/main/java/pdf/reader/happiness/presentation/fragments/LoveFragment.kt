package pdf.reader.happiness.presentation.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import pdf.reader.happiness.data.models.InfoModel
import pdf.reader.happiness.databinding.FragmentLoveBinding
import pdf.reader.happiness.presentation.ReadingActivity
import pdf.reader.happiness.presentation.adapter.ItemAdapter
import pdf.reader.happiness.vm.LoveViewModel


@KoinApiExtension
class LoveFragment : Fragment(), ItemAdapter.OnClick,KoinComponent{

    private lateinit var binding : FragmentLoveBinding
    private lateinit var itemAdapter: ItemAdapter
    private val viewModel:LoveViewModel = get()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentLoveBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemAdapter = ItemAdapter(this)
        binding.rv.adapter = itemAdapter
        CoroutineScope(Dispatchers.Main).launch {
            updateData()
        }
    }

    private suspend fun updateData() {
        while (true) {
            CoroutineScope(Dispatchers.Main).launch {
                viewModel.fetchLove().observeForever {
                    itemAdapter.update(it)
                    Log.d("pos","LOVE = $it")
                }
            }
            delay(1000)
        }
    }

    @KoinApiExtension
    override fun onClick(infoModel: InfoModel) {
        val intent = Intent(requireActivity(), ReadingActivity::class.java)
        intent.putExtra("data",infoModel)
        startActivity(intent)
    }
}