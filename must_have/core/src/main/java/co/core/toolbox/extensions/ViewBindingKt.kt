package co.core.toolbox.extensions

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import kotlin.reflect.KClass

fun <B : ViewBinding> KClass<B>.createInstance(lInflater: LayoutInflater): B {
    val javaClazz = java
    val method = javaClazz.getDeclaredMethod("inflate", LayoutInflater::class.java)
    return method.invoke(null, lInflater) as B
}

fun <B : ViewBinding> KClass<B>.createInstance(lInflater: LayoutInflater, container: ViewGroup?, attachRoot: Boolean): B {
    val javaClazz = java
    val method = javaClazz.getDeclaredMethod("inflate", LayoutInflater::class.java, ViewGroup::class.java, Boolean::class.javaPrimitiveType)
    return method.invoke(null, lInflater, container, attachRoot) as B
}

fun <B : ViewBinding> KClass<B>.createInstance(container: ViewGroup): B {
    val javaClazz = java
    val lInflater = LayoutInflater.from(container.context)
    val method = javaClazz.getDeclaredMethod("inflate", LayoutInflater::class.java, ViewGroup::class.java, Boolean::class.javaPrimitiveType)
    return method.invoke(null, lInflater, container, false) as B
}

fun <B : ViewBinding> KClass<B>.createInstance(context: Context): B {
    return createInstance(LayoutInflater.from(context))
}