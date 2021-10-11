package co.core.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import co.core.toolbox.extensions.createInstance
import kotlin.reflect.KClass

open class ViewStrategy<V : RecyclerView.ViewHolder, B : ViewBinding>(
        private val bindingClass: KClass<B>,
        val createVH: (parent: ViewGroup, binding: B) -> V) {

    fun getBindingInstance(parent: ViewGroup): B {
        return bindingClass.createInstance(parent)
    }
}