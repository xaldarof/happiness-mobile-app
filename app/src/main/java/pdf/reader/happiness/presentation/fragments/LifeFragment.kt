package pdf.reader.happiness.presentation.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get
import org.koin.core.component.KoinApiExtension
import pdf.reader.happiness.core.InfoModel
import pdf.reader.happiness.databinding.FragmentLifeBinding
import pdf.reader.happiness.presentation.ReadingActivity
import pdf.reader.happiness.presentation.adapter.ItemAdapter
import pdf.reader.happiness.vm.LifeViewModel


class LifeFragment : Fragment(), ItemAdapter.OnClick {

    private lateinit var binding: FragmentLifeBinding
    private lateinit var itemAdapter: ItemAdapter
    private val viewModel: LifeViewModel = get()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLifeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemAdapter = ItemAdapter(this)
        binding.rv.adapter = itemAdapter
    }

    override fun onResume() {
        super.onResume()
        CoroutineScope(Dispatchers.Main).launch {
            updateData()
        }
    }

    private suspend fun updateData() {
        viewModel.fetchLife().observeForever {
            itemAdapter.update(it)
        }
    }

    @KoinApiExtension
    override fun onClick(infoModel: InfoModel) {
        val intent = Intent(requireActivity(), ReadingActivity::class.java)
        intent.putExtra("data", infoModel)
        startActivity(intent)
    }

}