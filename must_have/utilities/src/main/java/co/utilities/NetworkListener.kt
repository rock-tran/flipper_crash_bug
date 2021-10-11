package co.utilities

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkInfo

/**
 * @author TUNGDX
 */

/**
 * Use for register network listener in [Activity] or in [android.app.Fragment]
 */
class NetworkListener(private var mActivity: Activity?, private val mOnNetworkListener: NetworkListener.OnNetworkListener?) : BroadcastReceiver() {
    interface OnNetworkListener {
        fun onNetworkConnected()

        fun onNetworkDisconnected()
    }

    private var connectivityManager: ConnectivityManager? = null
    private var activeNetInfo: NetworkInfo? = null

    /**
     * register network listener.
     */
    fun register() {
        if (mActivity == null)
            return
        val filter = IntentFilter(
                ConnectivityManager.CONNECTIVITY_ACTION)
        mActivity!!.registerReceiver(this, filter)

    }

    /**
     * unregister network listener. Must call this method when not need check
     * network state avoid memory leak.
     */
    fun unRegister() {
        if (mActivity == null)
            return
        mActivity!!.unregisterReceiver(this)
        mActivity = null
    }

    override fun onReceive(context: Context, intent: Intent) {
        if (mActivity == null)
            return
        connectivityManager = mActivity!!
                .applicationContext.getSystemService(
                Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            activeNetInfo = connectivityManager!!.activeNetworkInfo
        }
        if (activeNetInfo != null && activeNetInfo!!.isConnected) {
            mOnNetworkListener?.onNetworkConnected()
        } else {
            mOnNetworkListener?.onNetworkDisconnected()
        }

    }
}
