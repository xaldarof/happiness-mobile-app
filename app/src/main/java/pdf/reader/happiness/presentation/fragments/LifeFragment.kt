package pdf.reader.happiness.presentation.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get
import pdf.reader.happiness.data.models.InfoModel
import pdf.reader.happiness.databinding.FragmentLifeBinding
import pdf.reader.happiness.presentation.ReadingActivity
import pdf.reader.happiness.presentation.adapter.ItemAdapter
import pdf.reader.happiness.vm.LifeViewModel


class LifeFragment : Fragment() , ItemAdapter.OnClick {

    private lateinit var binding : FragmentLifeBinding
    private lateinit var adapter: ItemAdapter
    private val viewModel: LifeViewModel = get()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLifeBinding.inflate(inflater, container, false)
        adapter = ItemAdapter(this,requireContext())
        binding.rv.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        CoroutineScope(Dispatchers.Main).launch {
            viewModel.fetchLife().observeForever {
                adapter.update(it)
            }
        }
    }

    override fun onClick(infoModel: InfoModel) {
        val intent = Intent(requireActivity(), ReadingActivity::class.java)
        intent.putExtra("data",infoModel)
        startActivity(intent)
    }

}