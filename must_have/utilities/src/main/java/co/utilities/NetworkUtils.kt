package co.utilities

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

/**
 * @author TUNGDX
 */

/**
 * All utils for network.
 */
object NetworkUtils {

    /**
     * @param context
     * *
     * @return true if network conntected, false otherwise.
     */
    fun isNetworkConnected(context: Context): Boolean {
        val connectivityManager = context
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        if (networkInfo != null && networkInfo.isConnected) {
            return true
        }
        return false
    }

    /**
     * @param context
     * *
     * @return true if network connectivity exists or is in the process of being
     * * established, false otherwise.
     */
    fun isNetworkConnectedOrConnecting(context: Context): Boolean {
        val connectivityManager = context
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        if (networkInfo != null && networkInfo.isConnectedOrConnecting) {
            return true
        }
        return false
    }
}
