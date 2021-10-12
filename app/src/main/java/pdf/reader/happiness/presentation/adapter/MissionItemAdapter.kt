package pdf.reader.happiness.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pdf.reader.happiness.R
import pdf.reader.happiness.core.MissionModel

class MissionItemAdapter : RecyclerView.Adapter<MissionItemAdapter.VH>(){

    private val oldList = ArrayList<MissionModel>()

    inner class VH(private val view:View):RecyclerView.ViewHolder(view){

        val textView: TextView = view.findViewById(R.id.name)

//        fun onBind(missionModel: MissionModel){
//            textView = view.findViewById(R.id.name)
//        }
    }

    fun update(newList:List<MissionModel>){
        oldList.clear()
        oldList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.mission_item,parent,false)
        return VH(view)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.textView.text = oldList[position].name

    }

    override fun getItemCount(): Int {
        return oldList.size
    }
}