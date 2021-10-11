package pdf.reader.happiness.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
import pdf.reader.happiness.tools.CacheClear
import pdf.reader.happiness.tools.ClearDialog
import pdf.reader.happiness.tools.RestartDialog
import pdf.reader.happiness.tools.openPlayMarket

@KoinApiExtension
class SettingsFragment : Fragment(),KoinComponent,SettingFragmentPresenter.SettingsView,ClearDialog.ClearDialogCallBack {

    private lateinit var binding: FragmentSettingsBinding
    private val themeController: ThemeController by inject()
    private val fontController:FontController by inject()
    private lateinit var settingFragmentPresenter: SettingFragmentPresenter
    private val cacheClear:CacheClear by inject()
    private val allChaptersFinished:AllChaptersFinished by inject()
    private var isDeveloperModeEnabled = false
    private var clickCount = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        settingFragmentPresenter = SettingFragmentPresenter(this,themeController,fontController,allChaptersFinished)

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
            clickCount++
            if (clickCount>15) {
                isDeveloperModeEnabled = true
                Toast.makeText(requireContext(), R.string.dev_mode_enabled, Toast.LENGTH_SHORT).show()
            }

            if (settingFragmentPresenter.isAllChaptersFinished() || isDeveloperModeEnabled){
                startActivity(Intent(requireContext(),DataImportingActivity::class.java))
            }else {
                Toast.makeText(requireContext(), R.string.not_finished_all, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        CoroutineScope(Dispatchers.Main).launch {
            if (allChaptersFinished.isAllChaptersFinished()) {
                binding.implementBtn.setImageResource(R.drawable.ic_baseline_lock_open_24)
            }else{
                binding.implementBtn.setImageResource(R.drawable.ic_baseline_lock_24)
            }
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
