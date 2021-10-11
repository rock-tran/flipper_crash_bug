package com.project.screens

import android.app.Application
import co.core.mvvm.BaseViewModel
import co.core.toolbox.SchedulersFacade
import com.project.api.MainService

class DemoViewModel(schedulersFacade: SchedulersFacade, application: Application,
                    private val mainService: MainService)
    : BaseViewModel(schedulersFacade, application) {

    fun getData() {
        shortCallAPI(mainService.demoAPI())
    }
}

