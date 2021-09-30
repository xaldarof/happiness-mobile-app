package pdf.reader.happiness

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import pdf.reader.happiness.data.settings_cache.ThemeController
import pdf.reader.happiness.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var themeController: ThemeController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        sharedPreferences = requireContext().getSharedPreferences("cache",MODE_PRIVATE)
        themeController = ThemeController.Base(sharedPreferences)

        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        binding.themeSwitch.isChecked = themeController.isDarkThemeOn()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.themeSwitch.setOnCheckedChangeListener { p0, p1 ->
            if (p0!!.isChecked) {
                themeController.setTheme(true)
                AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
            } else {
                themeController.setTheme(false)
                AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
            }
        }

    }
}
