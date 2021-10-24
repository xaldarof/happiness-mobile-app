package pdf.reader.happiness.presentation.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pdf.reader.happiness.R
import pdf.reader.happiness.core.MusicModel
import pdf.reader.happiness.databinding.MusicItemBinding
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList

class MusicItemAdapter(private val callback: CallBack) :
    RecyclerView.Adapter<MusicItemAdapter.VH>() {

    private val oldList = ArrayList<MusicModel>()

    fun update(newList: List<MusicModel>) {
        oldList.clear()
        oldList.addAll(newList)
        notifyDataSetChanged()
    }

    fun onFinish(){
        if (oldList.isNotEmpty()) {
            oldList.forEach {
                it.isPlaying = false
            }
            notifyDataSetChanged()
        }
    }

    inner class VH(private val musicItemBinding: MusicItemBinding) :
        RecyclerView.ViewHolder(musicItemBinding.root) {

        val textView = musicItemBinding.musicName
        val durationTv = musicItemBinding.durationTv
        val container = musicItemBinding.musicContainer
        val playStop = musicItemBinding.stopPlay
        val playingIcon = musicItemBinding.playingIcon
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(MusicItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    private var playingPosition = 0

    override fun onBindViewHolder(holder: VH, @SuppressLint("RecyclerView") position: Int) {
        holder.textView.text = oldList[position].name
        holder.durationTv.text = oldList[position].duration
        val clickedItem = oldList[position]
        setAnimation(holder.itemView,position)


        if (oldList[position].isPlaying) {
            playingPosition = position
            holder.container.background.setColorFilter(ContextCompat.getColor(holder.container.context,R.color.time_color), PorterDuff.Mode.SRC_ATOP)
            holder.playStop.setImageResource(R.drawable.ic_baseline_pause_circle_outline_24)
            holder.playingIcon.visibility = View.VISIBLE
        } else {
            holder.container.background.setColorFilter(ContextCompat.getColor(holder.container.context,R.color.light_black), PorterDuff.Mode.SRC_ATOP)
            holder.playStop.setImageResource(R.drawable.ic_baseline_play_circle_outline_24)
            holder.playingIcon.visibility = View.INVISIBLE

        }

        holder.container.setOnClickListener {
            oldList[playingPosition].isPlaying = false
            clickedItem.isPlaying = true

            notifyDataSetChanged()
            callback.onMusicChange(oldList[position])

        }
    }

    private var lastPosition = -1

    private fun setAnimation(viewToAnimate: View, position: Int) {
        if (position > lastPosition) {
            val animation: Animation =
                AnimationUtils.loadAnimation(viewToAnimate.context, android.R.anim.slide_in_left)
            animation.duration = 800
            viewToAnimate.startAnimation(animation)
            lastPosition = position
        }
    }

    override fun getItemCount(): Int {
        return oldList.size
    }

    interface CallBack {

        fun onMusicChange(musicModel: MusicModel)

    }
}