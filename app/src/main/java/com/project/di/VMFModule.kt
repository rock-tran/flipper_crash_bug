package com.project.di

import android.app.Application
import co.core.toolbox.SchedulersFacade
import com.project.api.MainService
import dagger.Module
import dagger.Provides

@Module
class VMFModule {

    @Provides
    fun providesAppVMFactory(schedulersFacade: SchedulersFacade,
                             application: Application,
                             mainService: MainService): AppVMPFactory {
        return AppVMPFactory(schedulersFacade, application, mainService)
    }
}