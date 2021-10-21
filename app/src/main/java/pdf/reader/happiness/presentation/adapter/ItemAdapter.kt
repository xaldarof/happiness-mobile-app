package pdf.reader.happiness.presentation.adapter

import android.graphics.Color
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pdf.reader.happiness.databinding.ItemBinding
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import pdf.reader.happiness.core.InfoModel
import pdf.reader.happiness.data.cache.models.Type

class ItemAdapter(private val onClick: OnClick) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val list = ArrayList<InfoModel>()
    private var lastPosition = -1

    fun update(newList: List<InfoModel>) {
        val diffUtil = DiffUtill(list, newList)
        val diff = DiffUtil.calculateDiff(diffUtil)
        list.clear()
        list.addAll(newList)
        diff.dispatchUpdatesTo(this)

    }

    private inner class ItemViewHolder(private val itemBinding: ItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun onBind(infoModel: InfoModel) {
            itemBinding.titleTv.text = infoModel.title
            itemBinding.container.setOnClickListener { onClick.onClick(infoModel) }

            if (infoModel.finished && infoModel.isOpened) {
                itemBinding.stateIcon.background.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP)
            }
            else if (infoModel.isOpened) {
                itemBinding.stateIcon.background.setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP)
            }
            else {
                if (infoModel.dataType == Type.CLOUD) {
                    itemBinding.stateIcon.background.setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_ATOP)
                } else {
                    itemBinding.stateIcon.background.setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemViewHolder(
            ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
                AnimationUtils.loadAnimation(viewToAnimate.context, android.R.anim.slide_in_left)
            animation.duration = 500
            viewToAnimate.startAnimation(animation)
            lastPosition = position
        }
    }

    interface OnClick {
        fun onClick(infoModel: InfoModel)
    }
}