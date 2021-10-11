package co.utilities

import android.os.Build

/**
 * @author TUNGDX
 */

/**
 * All utils relative to Operating System.
 */
object OSUtils {

    fun hasJELLY(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN
    }

    fun hasKITKAT(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT
    }

    fun hasMarshmallow(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
    }
}
