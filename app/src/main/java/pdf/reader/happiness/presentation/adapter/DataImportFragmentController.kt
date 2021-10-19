package pdf.reader.happiness.presentation.adapter

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import pdf.reader.happiness.R

@SuppressLint("UseCompatLoadingForDrawables")
class DataImportFragmentController(
    private val activity: AppCompatActivity,
    private val fragments: List<Fragment>) : ViewPager2.OnPageChangeCallback(){

    private var viewPager2: ViewPager2 = activity.findViewById(R.id.pagerImportActivity)

    private var fragmentAdapter: FragmentAdapter =
        FragmentAdapter(activity.supportFragmentManager, activity.lifecycle, fragments)


    init {
        viewPager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        viewPager2.adapter = fragmentAdapter
        viewPager2.offscreenPageLimit = fragmentAdapter.itemCount

        viewPager2.apply {
            registerOnPageChangeCallback(this@DataImportFragmentController)
            (getChildAt(0) as RecyclerView).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        }
    }
}