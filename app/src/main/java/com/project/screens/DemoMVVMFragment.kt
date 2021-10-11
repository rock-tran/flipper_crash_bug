package com.project.screens

import androidx.recyclerview.widget.LinearLayoutManager
import co.core.adapter.MultipleTypeAdapter
import co.core.adapter.RowContainer
import co.core.adapter.ViewStrategy
import co.core.mvvm.BaseMVVMFragment
import com.base.databinding.FragmentDemoBinding
import com.base.databinding.ItemIntBinding
import com.project.di.AppVMPFactory

class DemoMVVMFragment : BaseMVVMFragment<DemoViewModel, AppVMPFactory, FragmentDemoBinding>() {

    override val setupViews: (FragmentDemoBinding.() -> Unit) = {
        imgDemo.setImageURI("http://streaming1.danviet.vn/upload/4-2017/images/2017-12-07/5-1512627579-width640height960.jpg")
        rvMain.layoutManager = LinearLayoutManager(context)

        val data = listOf(
            RowContainer(0, 1, 0),
            RowContainer(0, 1, 0)
        )

        val adapter = MultipleTypeAdapter.Builder()
            .register(0, ViewStrategy(ItemIntBinding::class)
            { _, itemView -> IntVH(itemView) })
            .build()
        rvMain.adapter = adapter
        adapter.submitList(data)
        viewModel.getData()
    }
}