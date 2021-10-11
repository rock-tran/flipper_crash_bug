package co.core.toolbox.extensions

import android.widget.EditText
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

fun EditText.getTextAsString(): String {
    return this.text.toString()
}

fun Fragment.showToast(toast: String?) {
    Toast.makeText(activity!!, toast, Toast.LENGTH_LONG).show()
}

fun Fragment.showToast(@StringRes textRes: Int) {
    Toast.makeText(activity!!, textRes, Toast.LENGTH_LONG).show()
}