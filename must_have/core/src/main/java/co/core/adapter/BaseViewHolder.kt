package co.core.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseViewHolder<O, B : ViewBinding>(val binding: B)
    : RecyclerView.ViewHolder(binding.root) {

    abstract val bindView: B.(item: O) -> Unit

    protected fun context(): Context {
        return itemView.context.applicationContext
    }

    fun triggerAction(listener: OnActionItemListener?) {
        listener ?: return
        val pos = adapterPosition
        if (pos == RecyclerView.NO_POSITION) return

        listener.onAction(pos)
    }
}