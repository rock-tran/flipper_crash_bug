package co.core.adapter

import androidx.viewbinding.ViewBinding

class NothingVH<T>(binding: ViewBinding) : BaseViewHolder<T, ViewBinding>(binding) {

    override val bindView: ViewBinding.(item: T) -> Unit = {
        //DO NOTHING
    }
}