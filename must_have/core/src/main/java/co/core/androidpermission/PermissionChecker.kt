package co.core.androidpermission

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.core.content.ContextCompat
import java.util.*

/**
 * Created by freesky1102 on 11/14/17.
 */
object PermissionChecker {

    interface OnCheckPersResultListener {

        fun onGranted(pers: ArrayList<String>)

        fun onDenied(pers: ArrayList<String>)
    }

    fun checkPermission(pers: Array<String>, fragment: Fragment, requestCode: Int) {
        fragment.requestPermissions(pers, requestCode)
    }

    fun checkPermission(pers: Array<String>, activity: Activity, requestCode: Int) {
        ActivityCompat.requestPermissions(activity, pers, requestCode)
    }

    fun onRequestPermissionResult(permissions: Array<String>, grantResults: IntArray, listener: OnCheckPersResultListener?) {

        if (listener == null) return

        val grantedList = ArrayList<String>()
        val deniedList = ArrayList<String>()

        if (permissions.isNotEmpty()) {

            for (index in permissions.indices) {

                val per = permissions[index]
                val isGranted = grantResults[index] == PackageManager.PERMISSION_GRANTED

                if (isGranted)
                    grantedList.add(per)
                else
                    deniedList.add(per)
            }
        }

        if (!grantedList.isEmpty()) {
            listener.onGranted(grantedList)
        }

        if (!deniedList.isEmpty()) {
            listener.onDenied(deniedList)
        }
    }

    fun isGranted(per: String, context: Context): Boolean {
        return ContextCompat.checkSelfPermission(context, per) == PackageManager.PERMISSION_GRANTED
    }
}
