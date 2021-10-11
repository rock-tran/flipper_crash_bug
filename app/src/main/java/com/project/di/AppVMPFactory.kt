package com.project.di

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import co.core.toolbox.SchedulersFacade
import com.project.api.MainService
import com.project.screens.DemoViewModel
import kotlin.reflect.KClass

@Suppress("UNCHECKED_CAST")
class AppVMPFactory(private val schedulersFacade: SchedulersFacade,
                    private val application: Application,
                    private val mainService: MainService) : ViewModelProvider.Factory {

    private val genVMMap: Map<KClass<*>, (() -> Any)>

    init {
        genVMMap = mapOf(
                DemoViewModel::class to { DemoViewModel(schedulersFacade, application, mainService) }
        )
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return genVMMap.getValue((modelClass as Class<*>).kotlin).invoke() as T
    }
}