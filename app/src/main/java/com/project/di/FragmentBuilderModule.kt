package com.project.di

import com.project.screens.DemoMVVMFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector(modules = [VMFModule::class])
    abstract fun bindDemoMVVMFragment(): DemoMVVMFragment

}