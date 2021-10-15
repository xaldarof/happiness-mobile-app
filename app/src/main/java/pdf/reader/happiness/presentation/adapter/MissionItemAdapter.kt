package pdf.reader.happiness.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pdf.reader.happiness.R
import pdf.reader.happiness.core.FragmentName
import pdf.reader.happiness.core.MissionModel
import pdf.reader.happiness.databinding.MissionItemBinding

class MissionItemAdapter(private val onClick: OnClick) :
    RecyclerView.Adapter<MissionItemAdapter.VH>() {

    private val oldList = ArrayList<MissionModel>()

    init {
        oldList.add(MissionModel("Медитация",0f,FragmentName.MEDITATION))
        oldList.add(MissionModel("Статистика",0f,FragmentName.STATISTIC))
        oldList.add(MissionModel("Бонусы",0f,FragmentName.BONUS))

    }

    inner class VH(private val binding: MissionItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(missionModel: MissionModel) {
            binding.containerMain.setOnClickListener {
                onClick.onClickOpen(missionModel)
            }

            binding.name.text = missionModel.name

            if (missionModel.fragmentName == FragmentName.MEDITATION) {
                binding.icon.setImageResource(R.drawable.ic_baseline_headset_24)
            }

            if (missionModel.fragmentName == FragmentName.STATISTIC) {
                binding.icon.setImageResource(R.drawable.ic_baseline_show_chart_24)
            }
            if (missionModel.fragmentName == FragmentName.BONUS) {
                binding.icon.setImageResource(R.drawable.ic_baseline_cake_24)
            }

        }
    }

    fun update(newList: List<MissionModel>) {
        oldList.clear()
        oldList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(MissionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
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