package co.core.actionbar

import androidx.annotation.LayoutRes
import android.view.ViewGroup

interface CustomActionbar {
    fun hide()

    fun show()

    fun inflateLayout(@LayoutRes layoutRes: Int)

    val actionbarView: ViewGroup
}
