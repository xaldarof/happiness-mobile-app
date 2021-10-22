package pdf.reader.happiness.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pdf.reader.happiness.core.MusicModel
import pdf.reader.happiness.databinding.MusicItemBinding

class MusicItemAdapter(private val callback:MusicItemAdapter.CallBack): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val oldList = ArrayList<MusicModel>()

    fun update(newList:List<MusicModel>) {
        oldList.clear()
        oldList.addAll(newList)
        notifyDataSetChanged()
    }

    private inner class VH(private val musicItemBinding: MusicItemBinding) :
        RecyclerView.ViewHolder(musicItemBinding.root) {

        fun onBind(musicModel: MusicModel) {
            musicItemBinding.musicName.text = musicModel.name

            musicItemBinding.playBtn.setOnClickListener { callback.onClickPlay(musicModel) }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return VH(MusicItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as VH).onBind(oldList[position])
    }

    override fun getItemCount(): Int {
        return oldList.size
    }

    interface CallBack {

        fun onClickPlay(musicModel: MusicModel){

        }
    }
}