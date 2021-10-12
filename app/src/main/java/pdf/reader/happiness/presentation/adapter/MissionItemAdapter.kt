package pdf.reader.happiness.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pdf.reader.happiness.core.MissionModel
import pdf.reader.happiness.databinding.MissionItemBinding

class MissionItemAdapter(private val onClick:OnClick): RecyclerView.Adapter<MissionItemAdapter.VH>(){

    private val oldList = ArrayList<MissionModel>()
    init {
        oldList.add(MissionModel("Temur",14f))
    }

    inner class VH(private val binding:MissionItemBinding):RecyclerView.ViewHolder(binding.root){

        fun onBind(missionModel: MissionModel){
            binding.containerMain.setOnClickListener {
                onClick.onClickOpen(missionModel)
            }
        }
    }

    fun update(newList:List<MissionModel>){
        oldList.clear()
        oldList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = MissionItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return VH(view)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(oldList[position])

    }

    override fun getItemCount(): Int {
        return oldList.size
    }
    interface OnClick {
        fun onClickOpen(missionModel: MissionModel)
    }
}