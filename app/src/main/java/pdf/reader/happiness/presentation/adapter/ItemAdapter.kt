package pdf.reader.happiness.presentation.adapter

import android.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pdf.reader.happiness.data.models.SuccessModel
import pdf.reader.happiness.databinding.ItemBinding
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import pdf.reader.happiness.data.models.InfoModel


class ItemAdapter(private val onClick: OnClick,private val context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val list = ArrayList<InfoModel>()
    private var lastPosition = -1

    fun update(newList:List<InfoModel>){
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    private inner class ItemViewHolder(private val itemBinding: ItemBinding):RecyclerView.ViewHolder(itemBinding.root){
        fun onBind(infoModel: InfoModel){
            itemBinding.titleTv.text = infoModel.title
            itemBinding.container.setOnClickListener { onClick.onClick(infoModel) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemViewHolder(ItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ItemViewHolder).onBind(list[position])
        setAnimation(holder.itemView,position)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    private fun setAnimation(viewToAnimate: View, position: Int) {
        if (position > lastPosition) {
            val animation: Animation = AnimationUtils.loadAnimation(viewToAnimate.context, R.anim.slide_in_left)
            animation.duration = 500
            viewToAnimate.startAnimation(animation)
            lastPosition = position
        }
    }

    interface OnClick {
        fun onClick(infoModel: InfoModel)
    }
}