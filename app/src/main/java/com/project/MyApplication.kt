package com.project

import co.core.imageloader.NImageLoader
import co.core.log.AppLogger
import co.core.log.setupAppLogger
import com.project.di.DaggerAppComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import org.slf4j.LoggerFactory
import javax.inject.Inject

/**
 * Created by freesky1102 on 5/14/16.
 */
class MyApplication : BuildVariantApplication(), HasAndroidInjector {

    @Inject
    lateinit var imageLoader: NImageLoader

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        myApplicationInstance = this

        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)

        setupAppLogger(LoggerFactory.getLogger("BaseApp"))
        AppLogger.debug("ToanTK")
    }

    override fun androidInjector() = dispatchingAndroidInjector

    companion object {

        private lateinit var myApplicationInstance: MyApplication

        fun get(): MyApplication {
            return myApplicationInstance
        }
    }
}