package co.utilities

import android.app.Activity
import android.content.Context
import android.os.Handler
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText

/**
 * @author TUNGDX
 */

/**
 * All utils for keyboard.
 */
object KeyboardUtils {

    /**
     * Hide keyboard.

     * @param activity
     */
    fun hideSoftKeyboard(activity: Activity) {
        val inputMethodManager = activity
                .getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        if (activity.currentFocus != null && activity.currentFocus!!.windowToken != null)
            inputMethodManager.hideSoftInputFromWindow(activity
                    .currentFocus!!.windowToken, 0)
    }

    /**
     * Hide keyboard when touch outside view.

     * @param activity
     * *
     * @param view     view root
     */
    fun hideKeyboard(activity: Activity?, view: View) {
        if (activity == null) {
            return
        }
        // Set up touch listener for non-text box views to hide keyboard.
        if (view !is EditText && view !is Button) {
            view.setOnTouchListener { v, event ->
                hideSoftKeyboard(activity)
                false
            }
        }

        // If a layout container, iterate over children and seed recursion.
        if (view is ViewGroup) {
            val size = view.childCount
            for (i in 0..size - 1) {
                val innerView = view.getChildAt(i)
                hideKeyboard(activity, innerView)
            }
        }
    }

    /**
     * Show keyboard

     * @param view
     */
    fun showKeyboard(view: View) {
        if (view.isFocused) {
            view.clearFocus()
        }
        view.requestFocus()
        val keyboard = view.context
                .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        keyboard.showSoftInput(view, InputMethodManager.SHOW_FORCED)
    }

    /**
     * Show keyboard after a delay

     * @param view
     * *
     * @param timeDelay
     * *
     * @return [Handler]. Should call
     * * [Handler.removeCallbacksAndMessages] with
     * * parameter= null when view is destroyed to avoid memory leak.
     */
    fun showDelayKeyboard(view: View?, timeDelay: Long): Handler {
        var timeDelay = timeDelay
        val handler = Handler()
        if (view == null || view.context == null) {
            return handler
        }

        if (timeDelay < 0) {
            timeDelay = 0
        }
        view.requestFocus()
        val delayRunnable = Runnable {
            val keyboard = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            keyboard.showSoftInput(view, InputMethodManager.SHOW_FORCED)
        }
        handler.postDelayed(delayRunnable, timeDelay)
        return handler
    }
}
