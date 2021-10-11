package com.project.di

import android.app.Application
import android.content.Context
import co.core.imageloader.NImageLoader
import com.base.imageloader.FrescoImageLoaderImpl
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.plugins.crashreporter.CrashReporterPlugin
import com.facebook.flipper.plugins.databases.DatabasesFlipperPlugin
import com.facebook.flipper.plugins.fresco.FrescoFlipperPlugin
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.facebook.flipper.plugins.sharedpreferences.SharedPreferencesFlipperPlugin
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
    fun providesOkHTTPClientBuilder(context: Context, networkPlugin: NetworkFlipperPlugin): OkHttpClient.Builder {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val chuckerCollector = ChuckerCollector(context = context,
                showNotification = true)
        val chuckerInterceptor = ChuckerInterceptor(collector = chuckerCollector,
                context = context)

        return OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(chuckerInterceptor)
                .addNetworkInterceptor(StethoInterceptor())
                .addNetworkInterceptor(FlipperOkhttpInterceptor(networkPlugin))
    }

    @Provides
    @Singleton
    fun providesNetworkFlipperPlugin(application: Application): NetworkFlipperPlugin {
        val plugin = NetworkFlipperPlugin()

        val client = AndroidFlipperClient.getInstance(application)
        val plugins = listOf(
                plugin,
                InspectorFlipperPlugin(application, DescriptorMapping.withDefaults()),
                SharedPreferencesFlipperPlugin(application),
                DatabasesFlipperPlugin(application),
                FrescoFlipperPlugin(),
                CrashReporterPlugin.getInstance()
        )

        plugins.forEach { client.addPlugin(it) }
        client.start()
        return plugin
    }
}