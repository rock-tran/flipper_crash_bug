package com.base

import android.content.Context
import co.utilities.Utils
import com.google.gson.Gson
import io.reactivex.rxjava3.core.Single
import java.lang.reflect.Type
import kotlin.reflect.KClass

class MockAPITool(private val context: Context) {

    fun <T> mockRequest(assetFileName: String, type: Type): Single<T> {
        return Single.create {
            try {
                val text = Utils.readAssetFile(context, assetFileName)
                val data = Gson().fromJson<T>(text, type)
                it.onSuccess(data)
            } catch (e: Exception) {
                it.onError(e)
            }
        }
    }

    fun <T : Any> mockRequest(assetFileName: String, type: KClass<T>): Single<T> {
        return Single.create {
            try {
                val text = Utils.readAssetFile(context, assetFileName)
                val data = Gson().fromJson(text, type.java)
                it.onSuccess(data)
            } catch (e: Exception) {
                it.onError(e)
            }
        }
    }
}