package pdf.reader.happiness.presentation.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import pdf.reader.happiness.R
import pdf.reader.happiness.core.TokenModel
import pdf.reader.happiness.databinding.TokenItemBinding
import pdf.reader.happiness.tools.copyToClipBoard
import pdf.reader.happiness.tools.formatToDate


class TokenHistoryItemAdapter(private val onClick: OnClick) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val list = ArrayList<TokenModel>()
    private var lastPosition = -1

    fun update(newList: List<TokenModel>) {
        val diffUtilCallBack = TokenHistoryDiffUtilCallBack(list,newList)
        val diffUtil = DiffUtil.calculateDiff(diffUtilCallBack,true)
        list.clear()
        list.addAll(newList)
        diffUtil.dispatchUpdatesTo(this)

    }

    private var isShowed = false

    private inner class ItemViewHolder(private val tokenItemBinding: TokenItemBinding) :
        RecyclerView.ViewHolder(tokenItemBinding.root) {

        fun onBind(token: TokenModel) {
            tokenItemBinding.tokenDate.text = token.tokenDate.toLong().formatToDate()
            tokenItemBinding.tokenId.text = "${token.tokenId.substring(0,6)}*******"
            tokenItemBinding.tokenCount.text = token.tokenValue.toString()

            tokenItemBinding.tokenId.setOnClickListener {
                if (isShowed) {
                    tokenItemBinding.tokenId.text = "${token.tokenId.substring(0,6)}*******"
                    isShowed = false
                }
                else {
                    tokenItemBinding.tokenId.text = token.tokenId
                    isShowed = true
                }
            }

            tokenItemBinding.deleteBtn.setOnClickListener { onClick.onClick(token) }
            tokenItemBinding.copyBtn.setOnClickListener {
                token.tokenId.copyToClipBoard(tokenItemBinding.root.context)
                Toast.makeText(tokenItemBinding.root.context, R.string.copy, Toast.LENGTH_SHORT).show()
            }

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