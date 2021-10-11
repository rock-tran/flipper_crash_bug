package co.utilities

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager.NameNotFoundException
import android.content.res.Resources
import android.hardware.Camera
import android.os.Looper
import android.provider.Settings
import android.telephony.TelephonyManager

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

/**
 * @author TUNGDX
 */

/**
 * All common of utils.
 */
object Utils {

    /**
     * Get version code of app.

     * @param context
     * *
     * @return version code.
     */
    fun getVersionCode(context: Context): Int {
        val info: PackageInfo
        try {
            info = context.packageManager.getPackageInfo(
                    context.packageName, 0)
            return info.versionCode
        } catch (e: NameNotFoundException) {
            e.printStackTrace()
        }

        // shoud be never happen.
        return 0
    }

    /**
     * Get version name of app.

     * @param context
     * *
     * @return version name.
     */
    fun getVersionName(context: Context): String? {
        val info: PackageInfo
        try {
            info = context.packageManager.getPackageInfo(
                    context.packageName, 0)
            return info.versionName
        } catch (e: NameNotFoundException) {
            e.printStackTrace()
        }

        // shoud be never happen.
        return null
    }

    /**
     * Md5

     * @param input
     * *
     * @return data after md5
     */
    fun md5(input: String): String {
        val MD5 = "MD5"
        try {
            // Create MD5 Hash
            val digest = MessageDigest.getInstance(MD5)
            digest.update(input.toByteArray())
            val messageDigest = digest.digest()

            // Create Hex String
            val hexString = StringBuilder()
            for (aMessageDigest in messageDigest) {
                var h = Integer.toHexString(0xFF and aMessageDigest.toInt())
                while (h.length < 2)
                    h = "0" + h
                hexString.append(h)
            }
            return hexString.toString()

        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }

        return ""
    }

    /**
     * Check sim card in device.

     * @param context
     * *
     * @return true if sim card exist. false otherwise.
     */
    fun isSimReady(context: Context): Boolean {
        val telephonyManager = context
                .getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        if (telephonyManager.simState == TelephonyManager.SIM_STATE_READY) {
            return true
        }
        return false
    }

    /**
     * [TelephonyManager.getDeviceId]

     * @param context
     * *
     * @return
     */
    fun getDeviceId(context: Context): String {
        //        if (OSUtils.hasMarshmallow()) {
        return Settings.Secure.getString(context.contentResolver,
                Settings.Secure.ANDROID_ID)
        //        }
        // If below Marshmallow
        //        TelephonyManager telephonyManager = (TelephonyManager) context
        //                .getSystemService(Context.TELEPHONY_SERVICE);
        //        String deviceId = telephonyManager.getDeviceId();
        //        if (TextUtils.isEmpty(deviceId)) {
        //            WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        //            deviceId = wifiManager.getConnectionInfo().getMacAddress();
        //        }
        //        if (TextUtils.isEmpty(deviceId)) {
        //            deviceId = Settings.Secure.getString(context.getContentResolver(),
        //                    Settings.Secure.ANDROID_ID);
        //        }
        //        return deviceId;
    }

    val isCameraUseByOtherApp: Boolean
        get() {
            var camera: Camera? = null
            try {
                camera = Camera.open()
            } catch (e: RuntimeException) {
                return true
            } finally {
                if (camera != null)
                    camera.release()
            }
            return false
        }

    //    // refers: http://stackoverflow.com/a/7167086/2128392
    //    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    //    public static int getActionbarHeight(Activity activity) {
    //        int attr;
    //        if (OSUtils.hasHoneycomb()) {
    //            attr = android.R.attr.actionBarSize;
    //        } else {
    //            attr = android.support.v7.appcompat.R.attr.actionBarSize;
    //        }
    //        final TypedArray styledAttributes = activity.getTheme()
    //                .obtainStyledAttributes(new int[]{attr});
    //        int actionbarSize = (int) styledAttributes.getDimension(0, 0);
    //        styledAttributes.recycle();
    //        return actionbarSize;
    //    }

    //    /**
    //     * Check the device to make sure it has the Google Play Services APK. If it
    //     * doesn't, display a dialog that allows users to download the APK from the
    //     * Google Play Store or enable it in the device's system settings.
    //     *
    //     * @param activity    Activity that check.
    //     * @param requestCode Request code pass from activity.
    //     */
    //    public static boolean checkPlayServices(Activity activity, int requestCode) {
    //        int resultCode = GoogleApiAvailability.getInstance()
    //                .isGooglePlayServicesAvailable(activity);
    //        if (resultCode != ConnectionResult.SUCCESS) {
    //            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
    //                GooglePlayServicesUtil.getErrorDialog(resultCode, activity,
    //                        requestCode).show();
    //            } else {
    //                Log.w("GooglePlayServices", "This device is not supported.");
    //            }
    //            return false;
    //        }
    //        return true;
    //    }

    fun ensureOnMainThread() {
        if (Looper.myLooper() != Looper.getMainLooper())
            throw IllegalStateException(
                    "This method must be called from the UI thread.")
    }

    fun convertDpToPx(context: Context, dp: Int): Float {
        return context.resources.displayMetrics.density * dp
    }

    fun getDimensionInDp(context: Context, resId: Int): Int {
        val resources = context.resources
        return (resources.getDimension(resId) / resources.displayMetrics.density).toInt()
    }

    fun readAssetFile(context: Context, fileName: String): String? {
        var input: BufferedReader? = null
        try {
            input = BufferedReader(InputStreamReader(context.assets.open(fileName)))
            var line: String?
            val buffer = StringBuilder()
            line = input.readLine()
            while (line != null) {
                buffer.append(line)
                line = input.readLine()
            }

            input.close()
            return buffer.toString()
        } catch (e: IOException) {
            if (input != null)
                try {
                    input.close()
                } catch (e1: IOException) {
                    e1.printStackTrace()
                }

            e.printStackTrace()
        }

        return null
    }
}
