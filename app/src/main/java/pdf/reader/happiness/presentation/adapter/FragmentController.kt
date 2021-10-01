package pdf.reader.happiness.presentation.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.PorterDuff
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import pdf.reader.happiness.R
import pdf.reader.simplepdfreader.presentation.adapter.FragmentAdapter

@SuppressLint("UseCompatLoadingForDrawables")
class FragmentController(
    private val activity: AppCompatActivity,
    private val fragments: List<Fragment>
) {

    private var viewPager2: ViewPager2 = activity.findViewById(R.id.pager)
    private var tabLayout: TabLayout

    private var fragmentAdapter: FragmentAdapter =
        FragmentAdapter(activity.supportFragmentManager, activity.lifecycle, fragments)


    init {
        viewPager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        tabLayout = activity.findViewById(R.id.tab_layout)
        viewPager2.adapter = fragmentAdapter
        viewPager2.offscreenPageLimit = fragmentAdapter.itemCount

        tabLayout.setOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab!!.icon?.setColorFilter(activity.resources.getColor(R.color.to_right_color), PorterDuff.Mode.SRC_IN)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab!!.icon?.setColorFilter(activity.resources.getColor(R.color.defColor), PorterDuff.Mode.SRC_IN)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })

        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            when (position) {
                0 -> {
                    tab.icon = activity.resources.getDrawable(R.drawable.ic_home)
                }

                1 -> {
                    tab.icon = activity.resources.getDrawable(R.drawable.ic_baseline_search_24)

                }

                2 -> {
                    tab.icon = activity.resources.getDrawable(R.drawable.ic_heart)

                }

                3 ->{
                    tab.icon = activity.resources.getDrawable(R.drawable.ic_baseline_settings_24)
                }

            }
        }.attach()
    }
}