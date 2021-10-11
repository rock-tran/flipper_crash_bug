package co.core.imageloader

import android.graphics.Bitmap
import android.view.View

interface NImageLoadingListener {
    /**
     * Is called when image loading task was started

     * @param imageUri Loading image URI
     * *
     * @param view     View for image
     */
    fun onLoadingStarted(imageUri: String, view: View)

    /**
     * Is called when an error was occurred during image loading

     * @param imageUri Loading image URI
     * *
     * @param view     View for image. Can be **null**.
     */
    fun onLoadingFailed(imageUri: String, view: View)

    /**
     * Is called when image is loaded successfully (and displayed in View if one
     * was specified)

     * @param imageUri    Loaded image URI
     * *
     * @param view        View for image. Can be **null**.
     * *
     * @param loadedImage Bitmap of loaded and decoded image
     */
    fun onLoadingComplete(imageUri: String?, view: View?, loadedImage: Bitmap?)

    /**
     * Is called when image loading task was cancelled because View for image
     * was reused in newer task

     * @param imageUri Loading image URI
     * *
     * @param view     View for image. Can be **null**.
     */
    fun onLoadingCancelled(imageUri: String, view: View)
}
