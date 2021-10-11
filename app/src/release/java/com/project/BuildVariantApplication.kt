package com.project

//import com.letv.sarrsdesktop.blockcanaryex.jrt.BlockCanaryEx;
//import com.letv.sarrsdesktop.blockcanaryex.jrt.Config;
import android.app.Application
import com.facebook.stetho.Stetho
import com.facebook.stetho.rhino.JsRuntimeReplFactoryBuilder

/**
 * Created by freesky1102 on 11/23/16.
 */

abstract class BuildVariantApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector {
                            Stetho.DefaultInspectorModulesBuilder(this).runtimeRepl(
                                    JsRuntimeReplFactoryBuilder(this)
                                            // Pass to JavaScript: var foo = "bar";
                                            .addVariable("foo", "bar")
                                            .build()).finish()
                        }
                        .build())
    }

}