package pdf.reader.happiness.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pdf.reader.happiness.R
import pdf.reader.happiness.data.models.InfoModel
import pdf.reader.happiness.data.models.SuccessModel
import pdf.reader.happiness.databinding.FragmentHappinessBinding
import pdf.reader.happiness.presentation.adapter.ItemAdapter

class HappinessFragment : Fragment(),ItemAdapter.OnClick {

    private lateinit var binding: FragmentHappinessBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHappinessBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ItemAdapter(this,requireContext())
    }

    override fun onClick(infoModel: InfoModel) {

    }
}