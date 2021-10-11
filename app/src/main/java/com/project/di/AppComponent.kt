package com.project.di

import com.project.MyApplication
import dagger.BindsInstance

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by freesky1102 on 8/14/16.
 */
@Singleton
@Component(modules = [DefaultAppModule::class, ApplicationModule::class, AndroidSupportInjectionModule::class,
    FragmentBuilderModule::class])
interface AppComponent : AndroidInjector<MyApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: MyApplication): Builder

        fun build(): AppComponent
    }
}