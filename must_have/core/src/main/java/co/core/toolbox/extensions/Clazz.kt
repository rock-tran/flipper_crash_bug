package co.core.toolbox.extensions

import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import kotlin.reflect.KClass

fun <T : Any> KClass<T>.getLastGenericTypeClass(): Class<*> {
    val typeArgs = getTypeArgs()
    val bindingType = typeArgs[typeArgs.lastIndex]

    return bindingType as Class<*>
}

fun <T : Any> KClass<T>.getGenericTypeClass(index: Int): Class<*> {
    val typeArgs = getTypeArgs()
    val bindingType = typeArgs[index]

    return bindingType as Class<*>
}

fun <T : Any> KClass<T>.getTypeArgs(): Array<Type> {
    val genericTypes = java.genericSuperclass
    if (genericTypes !is ParameterizedType)
        throw IllegalArgumentException("${this.simpleName} doesn't specify Generic")

    return genericTypes.actualTypeArguments
}