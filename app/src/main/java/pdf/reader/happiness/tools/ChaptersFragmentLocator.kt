package pdf.reader.happiness.tools

import android.os.Bundle
import androidx.fragment.app.Fragment
import org.koin.core.component.KoinApiExtension
import pdf.reader.happiness.R
import pdf.reader.happiness.core.ChapterModel
import pdf.reader.happiness.data.cache.models.ChapterModelDb
import pdf.reader.happiness.presentation.fragments.HappinessFragment
import pdf.reader.happiness.presentation.fragments.LifeFragment
import pdf.reader.happiness.presentation.fragments.LoveFragment
import pdf.reader.happiness.presentation.fragments.SuccessFragment

class ChaptersFragmentLocator(private val fragment: Fragment,private val chapterModel: ChapterModel){

    @KoinApiExtension
    fun locateFragment(fragmentName: ChapterModelDb.FragmentName) {
        if (fragmentName==ChapterModelDb.FragmentName.LIFE){
            navigate(LifeFragment())
        }

        if (fragmentName==ChapterModelDb.FragmentName.LOVE){
            navigate(LoveFragment())
        }

        if (fragmentName==ChapterModelDb.FragmentName.SUCCESS){
            navigate(SuccessFragment())
        }

        if (fragmentName==ChapterModelDb.FragmentName.HAPPY){
            navigate(HappinessFragment())
        }

    }

    private fun navigate(fragment: Fragment) {
        val bungle = Bundle()
        bungle.putSerializable("chapter",chapterModel)
        fragment.arguments = bungle

        this.fragment.parentFragmentManager.beginTransaction().replace(R.id.containerMain, fragment)
            .addToBackStack(null).commit()
    }
}