package pdf.reader.happiness

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import pdf.reader.happiness.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.darkTheme.setOnClickListener {
            Toast.makeText(requireContext(), "dark", Toast.LENGTH_SHORT).show()
            binding.darkTheme.setTextColor(Color.WHITE)
            binding.lightTheme.setTextColor(Color.BLACK)

        }
        binding.lightTheme.setOnClickListener {
            Toast.makeText(requireContext(), "LIGHT", Toast.LENGTH_SHORT).show()
            binding.darkTheme.setTextColor(Color.BLACK)
            binding.lightTheme.setTextColor(Color.WHITE)
        }
    }
}
