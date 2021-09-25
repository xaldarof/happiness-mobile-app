package pdf.reader.happiness.presentation.adapter

import android.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pdf.reader.happiness.data.models.InformationModel
import pdf.reader.happiness.databinding.ItemBinding
import android.view.animation.Animation
import android.view.animation.AnimationUtils


class ItemAdapter(private val onClick: OnClick,private val context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val list = ArrayList<InformationModel>()
    private var lastPosition = -1

    fun update(newList:List<InformationModel>){
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    private inner class ItemViewHolder(private val itemBinding: ItemBinding):RecyclerView.ViewHolder(itemBinding.root){
        fun onBind(informationModel: InformationModel){
            itemBinding.titleTv.text = informationModel.title
            itemBinding.container.setOnClickListener { onClick.onClick(informationModel) }
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
            animation.duration = 1000
            viewToAnimate.startAnimation(animation)
            lastPosition = position
        }
    }
    interface OnClick {
        fun onClick(informationModel: InformationModel)
    }
}