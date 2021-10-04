package pdf.reader.happiness.presentation.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import pdf.reader.happiness.data.models.InfoModel
import pdf.reader.happiness.databinding.FragmentSearchBinding
import pdf.reader.happiness.presentation.ReadingActivity
import pdf.reader.happiness.presentation.adapter.ItemAdapter
import pdf.reader.happiness.tools.TextWatcherCallBack
import pdf.reader.happiness.vm.SearchViewModel

@KoinApiExtension
class SearchFragment : Fragment(), ItemAdapter.OnClick, KoinComponent {

    private lateinit var itemAdapter: ItemAdapter
    private lateinit var binding: FragmentSearchBinding
    private val viewModel: SearchViewModel = get()
    private var realQuery = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemAdapter = ItemAdapter(this)
        binding.rv.adapter = itemAdapter

        binding.searchView.addTextChangedListener(TextWatcherCallBack.Base(object :
            TextWatcherCallBack.CustomTextWatcher {
            override fun onTextChange(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                searchFromDatabase(p0.toString())
            }
        }))
    }

    override fun onResume() {
        super.onResume()
        searchFromDatabase(realQuery)
    }

    private fun searchFromDatabase(query: String) {
        realQuery = "%$query%"
        viewModel.fetchSearchResult(realQuery).observeForever {
            itemAdapter.update(it)
        }
    }

    override fun onClick(infoModel: InfoModel) {
        val intent = Intent(requireActivity(), ReadingActivity::class.java)
        intent.putExtra("data", infoModel)
        startActivity(intent)
    }
}