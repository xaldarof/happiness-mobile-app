package pdf.reader.happiness.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ShareCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import pdf.reader.happiness.R
import pdf.reader.happiness.data.cache.settings_cache.AllChaptersFinished
import pdf.reader.happiness.data.cache.settings_cache.FontController
import pdf.reader.happiness.data.cache.settings_cache.ThemeController
import pdf.reader.happiness.databinding.FragmentSettingsBinding
import pdf.reader.happiness.tools.*

@KoinApiExtension
class SettingsFragment : Fragment(),KoinComponent,SettingFragmentPresenter.SettingsView,ClearDialog.ClearDialogCallBack {

    private lateinit var binding: FragmentSettingsBinding
    private val themeController: ThemeController by inject()
    private val fontController:FontController by inject()
    private lateinit var settingFragmentPresenter: SettingFragmentPresenter
    private val cacheClear:CacheClear by inject()
    private val allChaptersFinished:AllChaptersFinished by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        settingFragmentPresenter = SettingFragmentPresenter(this,themeController,
            fontController,allChaptersFinished)

        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        binding.themeSwitch.isChecked = themeController.isDarkThemeOn()
        binding.fontSwitch.isChecked = fontController.isBoldFont()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.themeSwitch.setOnCheckedChangeListener { p0, _ ->
            if (p0.isChecked) {
                settingFragmentPresenter.updateDarkThemeSwitchState(true)
            } else {
                settingFragmentPresenter.updateDarkThemeSwitchState(false)
            }
        }

        binding.fontSwitch.setOnCheckedChangeListener { p0, _ ->
            if (p0.isChecked) {
                settingFragmentPresenter.updateFontSwitchState(true)
            } else {
                settingFragmentPresenter.updateFontSwitchState(false)
            }
        }

        binding.clearBtn.setOnClickListener {
            ClearDialog.Base(this).show(requireContext())
        }
        binding.rateBtn.setOnClickListener {
            requireContext().openPlayMarket()
        }
        
        binding.importData.setOnClickListener {
           startActivity(Intent(requireContext(),DataImportingActivity::class.java))
        }

        binding.shareBtn.setOnClickListener {
            requireActivity().shareApp()
        }
    }

    override fun updateDarkThemeSwitchState(state:Boolean) {
        binding.themeSwitch.isChecked = state
    }

    override fun updateFontSwitchState(state: Boolean) {
        binding.fontSwitch.isChecked = state
    }

    override fun onClickYes() {
        cacheClear.clear()
        RestartDialog.Base().show(requireContext())
    }
}
