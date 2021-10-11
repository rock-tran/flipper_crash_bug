package co.core.imageloader

import android.view.View

/**
 * Listener for image loading progress.
 */
interface NImageLoadingProgressListener {
    /**
     * Is called when image loading progress changed.

     * @param imageUri Image URI
     * *
     * @param view     View for image. Can be **null**.
     * *
     * @param current  Downloaded size in bytes
     * *
     * @param total    Total size in bytes
     */
    fun onProgressUpdate(imageUri: String, view: View, current: Int, total: Int)
}
