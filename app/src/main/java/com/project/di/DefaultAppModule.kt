package com.project.di

import android.app.Application
import android.content.Context
import com.base.MockAPITool
import com.project.MyApplication
import com.project.api.MainService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class DefaultAppModule {

    @Provides
    @Singleton
    fun provideApplication(application: MyApplication): Application {
        return application
    }

    @Provides
    @Singleton
    fun provideContext(application: MyApplication): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun providesMainService(okHttpBuilder: OkHttpClient.Builder): MainService {
        return Retrofit.Builder().baseUrl("https://google.com.vn")
                .client(okHttpBuilder.build())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(MainService::class.java)
    }

    @Provides
    @Singleton
    fun providesMockAPITool(context: Context): MockAPITool {
        return MockAPITool(context)
    }
}