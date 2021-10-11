package com.project.screens

import co.core.adapter.BaseViewHolder
import com.base.databinding.ItemIntBinding

class IntVH(binding: ItemIntBinding) : BaseViewHolder<Int, ItemIntBinding>(binding) {

    override val bindView: ItemIntBinding.(item: Int) -> Unit = {
        tvValue.text = it.toString()
    }
}