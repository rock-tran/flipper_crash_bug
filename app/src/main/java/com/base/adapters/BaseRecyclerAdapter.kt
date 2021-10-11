package com.base.adapters

import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerAdapter<T> : RecyclerView.Adapter<AppViewHolder> {
    protected var data: MutableList<T>? = null

    constructor() {}

    constructor(data: MutableList<T>) {
        this.data = data
    }


    override fun getItemCount(): Int {
        if (data == null) return 0
        return data!!.size
    }

    operator fun get(index: Int): T? {
        if (data == null || data!!.isEmpty()) return null
        return data!![index]
    }

    fun clearAll() {
        if (data == null) return
        data!!.clear()
        notifyDataSetChanged()
    }

    fun insert(index: Int, objectList: List<T>?) {
        if (data == null || objectList == null || objectList.isEmpty()) return
        data!!.addAll(index, objectList)
        notifyItemRangeInserted(index + 1, objectList.size)
    }

    fun insert(index: Int, `object`: T) {
        if (data == null) return
        data!!.add(index, `object`)
        notifyItemInserted(index + 1)
    }

    fun addAll(message: List<T>) {
        if (data == null) return
        insert(data!!.size, message)
    }

    fun add(message: T) {
        if (data == null) return
        insert(data!!.size, message)
    }

    fun remove(index: Int) {
        if (data == null || data!!.size <= index) return

        data!!.removeAt(index)
        notifyDataSetChanged()
    }
}

