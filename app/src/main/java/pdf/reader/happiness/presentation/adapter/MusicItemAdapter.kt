package pdf.reader.happiness.presentation.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.PorterDuff
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pdf.reader.happiness.R
import pdf.reader.happiness.core.MusicModel
import pdf.reader.happiness.databinding.MusicItemBinding

class MusicItemAdapter(private val callback: CallBack) :
    RecyclerView.Adapter<MusicItemAdapter.VH>() {

    private val oldList = ArrayList<MusicModel>()

    fun update(newList: List<MusicModel>) {
        oldList.clear()
        oldList.addAll(newList)
        notifyDataSetChanged()
    }

    inner class VH(private val musicItemBinding: MusicItemBinding) :
        RecyclerView.ViewHolder(musicItemBinding.root) {

        val textView = musicItemBinding.musicName
        val container = musicItemBinding.musicContainer
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(MusicItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    private var playingPosition = 0

    override fun onBindViewHolder(holder: VH, @SuppressLint("RecyclerView") position: Int) {
        holder.textView.text = oldList[position].name
        val list = oldList[position]

        if (oldList[position].isPlaying) {
            playingPosition = position
            holder.container.background.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP)
        } else {
            holder.container.background.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP)

        }

        holder.container.setOnClickListener {
            oldList[playingPosition].isPlaying = false
            list.isPlaying = true
            notifyDataSetChanged()
            callback.onMusicChange(oldList[position])
        }
    }


    override fun getItemCount(): Int {
        return oldList.size
    }

    interface CallBack {

        fun onMusicChange(musicModel: MusicModel) {

        }
    }


}