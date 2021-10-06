package pdf.reader.happiness.presentation.adapter

import pdf.reader.happiness.databinding.ItemAchievmentBinding

import android.R

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import pdf.reader.happiness.core.AchievementModel
import pdf.reader.happiness.tools.DateFormatter

class AchievementAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val list = ArrayList<AchievementModel>()
    private var lastPosition = -1

    fun update(newList: List<AchievementModel>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    private inner class ItemViewHolder(private val binding: ItemAchievmentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(model: AchievementModel) {
            binding.achBodyTv.text = model.message
            binding.achDateTv.text = DateFormatter().getFormattedDateTime(model.date)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemViewHolder(ItemAchievmentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ItemViewHolder).onBind(list[position])
        setAnimation(holder.itemView, position)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    private fun setAnimation(viewToAnimate: View, position: Int) {
        if (position > lastPosition) {
            val animation: Animation =
                AnimationUtils.loadAnimation(viewToAnimate.context, R.anim.slide_in_left)
            animation.duration = 500
            viewToAnimate.startAnimation(animation)
            lastPosition = position
        }
    }
}