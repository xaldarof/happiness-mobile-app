package pdf.reader.happiness

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import pdf.reader.happiness.data.models.InfoModel
import pdf.reader.happiness.databinding.FragmentFavoritesBinding
import pdf.reader.happiness.presentation.ReadingActivity
import pdf.reader.happiness.presentation.adapter.ItemAdapter
import pdf.reader.happiness.tools.showIfEmpty
import pdf.reader.happiness.vm.FavoritesViewModel

@KoinApiExtension
class FavoritesFragment : Fragment(), ItemAdapter.OnClick, KoinComponent {

    private lateinit var binding: FragmentFavoritesBinding
    private lateinit var itemAdapter: ItemAdapter
    private val viewModel: FavoritesViewModel = get()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemAdapter = ItemAdapter(this)
        binding.rv.adapter = itemAdapter
        updateData()

    }

    override fun onResume() {
        super.onResume()
        updateData()
    }

    private fun updateData() {
        viewModel.fetchFavorites().observeForever {
            itemAdapter.update(it)
            binding.emptyTv.showIfEmpty(it)
            binding.img.showIfEmpty(it)
        }
    }


    override fun onClick(infoModel: InfoModel) {
        val intent = Intent(requireActivity(), ReadingActivity::class.java)
        intent.putExtra("data", infoModel)
        startActivity(intent)
    }
}