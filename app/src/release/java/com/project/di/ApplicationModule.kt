package com.project.di

import android.content.Context
import co.core.imageloader.NImageLoader
import com.base.imageloader.FrescoImageLoaderImpl
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.facebook.stetho.okhttp3.StethoInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

/**
 * Created by freesky1102 on 8/14/16.
 */
@Module
class ApplicationModule {

    @Provides
    @Singleton
    fun provideImageLoader(context: Context): NImageLoader {
        return FrescoImageLoaderImpl(context)
    }

    @Provides
    @Singleton
    fun providesOkHTTPClientBuilder(context: Context): OkHttpClient.Builder {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val chuckerCollector = ChuckerCollector(context = context,
                showNotification = true)
        val chuckerInterceptor = ChuckerInterceptor(collector = chuckerCollector,
                context = context)

        return OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addNetworkInterceptor(StethoInterceptor())
                .addInterceptor(chuckerInterceptor)
    }
}