package pdf.reader.happiness.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import pdf.reader.happiness.databinding.RegisterFragmentLayoutBinding

/**
 * @Author: Temur
 * @Date: 27/08/2022
 */

class RegisterFragment : Fragment() {

    private lateinit var binding: RegisterFragmentLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RegisterFragmentLayoutBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}