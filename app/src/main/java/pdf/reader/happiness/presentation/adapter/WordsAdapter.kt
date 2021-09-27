package pdf.reader.happiness.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import pdf.reader.happiness.R
import pdf.reader.happiness.databinding.WordsItemBinding

class WordsAdapter(private val list: List<String>,private var layoutInflater: LayoutInflater,private val context: Context): PagerAdapter() {
    override fun getCount(): Int {
        return list.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(R.layout.words_item,null)
        val wordsItemBinding = WordsItemBinding.bind(view)
        val viewPager = container as ViewPager
        viewPager.addView(view,0)
        wordsItemBinding.tvWord.text = list[position]
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val viewPager = container as ViewPager
        val view = `object` as View
        viewPager.removeView(view)
    }
}