package pdf.reader.simplepdfreader.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class FragmentAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    private val fragments: List<Fragment>
) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return fragments.size
    }
    override fun createFragment(position: Int): Fragment {
        if (position == 0) {
            return fragments[0]
        }
        if (position == 1) {
            return fragments[1]
        }
        if (position == 2) {
            return fragments[2]
        }
        if (position == 3) {
            return fragments[3]
        }
        if (position == 4) {
            return fragments[4]
        }
        if (position == 5) {
            return fragments[5]
        }

        return null!!
    }

}