package co.core.dialog

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.fragment.app.DialogFragment

/**
 * Created by freesky1102 on 8/28/16.
 */
abstract class NDialogFragment : DialogFragment() {

    private var mRequestCode: Int = 0

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = savedInstanceState ?: arguments

        if (bundle != null)
            getDataFrom(bundle)
    }

    @CallSuper
    protected open fun getDataFrom(bundle: Bundle) {
        mRequestCode = bundle.getInt(EXTRA_REQUEST_CODE)
    }

    @CallSuper
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(EXTRA_REQUEST_CODE, mRequestCode)
    }

    @CallSuper
    override fun onCancel(dialog: DialogInterface) {
        notifyToHost(null, ACTION_CANCEL)
    }

    protected fun notifyToHost(intent: Intent?, action: Int) {
        val responseFragment = parentFragment
        if (responseFragment != null && responseFragment is OnActionInDialogListener) {
            responseFragment.onDialogResult(this, mRequestCode, action, intent)
            return
        }

        val activity = activity
        if (activity != null && activity is OnActionInDialogListener) {
            activity.onDialogResult(this, mRequestCode, action, intent)
        }
    }

    companion object {

        const val ACTION_CANCEL = 0

        const val ACTION_SET = -1

        private const val EXTRA_REQUEST_CODE = "extra_request_code"

        fun putRequestCode(bundle: Bundle, requestCode: Int) {
            bundle.putInt(EXTRA_REQUEST_CODE, requestCode)
        }
    }
}
