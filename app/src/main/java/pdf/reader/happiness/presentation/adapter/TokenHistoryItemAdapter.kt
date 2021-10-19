package pdf.reader.happiness.presentation.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import pdf.reader.happiness.core.TokenModel
import pdf.reader.happiness.databinding.TokenItemBinding
import pdf.reader.happiness.tools.formatToDate


class TokenHistoryItemAdapter(private val onClick: OnClick) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val list = ArrayList<TokenModel>()
    private var lastPosition = -1

    fun update(newList: List<TokenModel>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }

    private inner class ItemViewHolder(private val tokenItemBinding: TokenItemBinding) :
        RecyclerView.ViewHolder(tokenItemBinding.root) {

        fun onBind(token: TokenModel) {
            tokenItemBinding.tokenDate.text = token.tokenDate.toLong().formatToDate()
            tokenItemBinding.tokenId.text = token.tokenId
            tokenItemBinding.tokenCount.text = token.tokenValue.toString()

            tokenItemBinding.deleteBtn.setOnClickListener { onClick.onClick(token) }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemViewHolder(
            TokenItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
        fun onClick(tokenModel: TokenModel)
    }
}