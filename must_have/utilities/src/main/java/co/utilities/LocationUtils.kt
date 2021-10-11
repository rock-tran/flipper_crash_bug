package co.utilities

import android.content.Context
import android.location.LocationManager

/**
 * @author TUNGDX
 */

/**
 * All utils for Location Service.
 */
object LocationUtils {

    /**
     * @param context
     * *
     * @return true if gps enabled or location network service enabled.
     */
    fun isLocationServiceEnabled(context: Context): Boolean {
        val gps_enabled: Boolean
        val network_enabled: Boolean
        val lm = context
                .getSystemService(Context.LOCATION_SERVICE) as LocationManager
        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER)
            network_enabled = lm
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        } catch (ex: Exception) {
            ex.printStackTrace()
            return false
        }

        return gps_enabled || network_enabled
    }

    /**
     * @param context
     * *
     * @return true if gps enabled.
     */
    fun isGPSEnabled(context: Context): Boolean {
        val lm = context
                .getSystemService(Context.LOCATION_SERVICE) as LocationManager
        try {
            return lm.isProviderEnabled(LocationManager.GPS_PROVIDER)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

        return false
    }

    /**
     * @param context
     * *
     * @return true if location service network enabled.
     */
    fun isLocationServiceNetworkEnabled(context: Context): Boolean {
        val lm = context
                .getSystemService(Context.LOCATION_SERVICE) as LocationManager
        try {
            return lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

        return false
    }
}
