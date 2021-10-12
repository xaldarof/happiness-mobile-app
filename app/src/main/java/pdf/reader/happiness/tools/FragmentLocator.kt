package pdf.reader.happiness.tools

import android.os.Bundle
import androidx.fragment.app.Fragment
import org.koin.core.component.KoinApiExtension
import pdf.reader.happiness.R
import pdf.reader.happiness.core.ChapterModel
import pdf.reader.happiness.core.FragmentName
import pdf.reader.happiness.data.cache.models.ChapterModelDb
import pdf.reader.happiness.presentation.fragments.*
import java.io.Serializable

class FragmentLocator<T>(
    private val fragment: Fragment,
    private val model: T
) {

    @KoinApiExtension
    fun locateFragment(fragmentName: FragmentName) {
        if (fragmentName == FragmentName.LIFE) {
            navigate(LifeFragment())
        }

        if (fragmentName == FragmentName.LOVE) {
            navigate(LoveFragment())
        }

        if (fragmentName == FragmentName.SUCCESS) {
            navigate(SuccessFragment())
        }

        if (fragmentName == FragmentName.HAPPY) {
            navigate(HappinessFragment())
        }

        if (fragmentName == FragmentName.MEDITATION) {
            navigate(MeditationFragment())
        }

    }

    private fun navigate(fragment: Fragment) {
        val bungle = Bundle()
        bungle.putSerializable("chapter", model as Serializable)
        fragment.arguments = bungle

        this.fragment.parentFragmentManager.beginTransaction().replace(R.id.containerMain, fragment)
            .addToBackStack(null).commit()
    }
}