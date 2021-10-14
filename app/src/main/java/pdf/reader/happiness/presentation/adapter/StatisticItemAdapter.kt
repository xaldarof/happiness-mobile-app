package pdf.reader.happiness.presentation.adapter

import android.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import pdf.reader.happiness.core.StatisticModel
import pdf.reader.happiness.databinding.StatisticItemBinding


class StatisticItemAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val list = ArrayList<StatisticModel>()
    private var lastPosition = -1

    fun update(newList: List<StatisticModel>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    private inner class ItemViewHolder(private val binding: StatisticItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(statisticModel: StatisticModel) {
            binding.statisticTitle.text = statisticModel.title
            binding.statisticInfo.text = statisticModel.name
            binding.statisticChapter.text = binding.statisticChapter.text.toString().plus(statisticModel.chapter)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemViewHolder(
            StatisticItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
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