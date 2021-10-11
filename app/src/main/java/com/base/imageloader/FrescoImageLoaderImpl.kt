package com.base.imageloader

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import co.core.imageloader.NDisplayOptions
import co.core.imageloader.NImageLoader
import co.core.imageloader.NImageLoadingListener
import co.core.imageloader.NImageLoadingProgressListener
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.common.ResizeOptions
import com.facebook.imagepipeline.core.ImagePipelineConfig
import com.facebook.imagepipeline.request.ImageRequest
import com.facebook.imagepipeline.request.ImageRequestBuilder
import java.io.File

/**
 * @author ToanTK
 */

/**
 * [Fresco<a></a>
 * library impls [NImageLoader] for default ImageLoader
 ](http://frescolib.org/) */
class FrescoImageLoaderImpl(private val mContext: Context) : NImageLoader {

    init {
        val config = ImagePipelineConfig.newBuilder(mContext)
                .setDownsampleEnabled(true)
                .build()

        Fresco.initialize(mContext, config)
    }

    override fun display(url: String?, imageView: ImageView?) {
        display(url, imageView, null, null, null)
    }

    override fun display(url: String?, imageView: ImageView?,
                         listener: NImageLoadingListener?) {
        display(url, imageView, null, listener, null)
    }

    override fun display(url: String?, imageView: ImageView?,
                         progressListener: NImageLoadingProgressListener?) {
        display(url, imageView, null, null, progressListener)
    }

    override fun display(url: String?, imageView: ImageView?,
                         listener: NImageLoadingListener?,
                         progressListener: NImageLoadingProgressListener?) {
        display(url, imageView, null, listener, progressListener)
    }

    override fun display(url: String?, imageView: ImageView?,
                         options: NDisplayOptions?) {
        display(url, imageView, options, null, null)
    }

    override fun display(url: String?, imageView: ImageView?,
                         options: NDisplayOptions?, listener: NImageLoadingListener?) {
        display(url, imageView, options, listener, null)
    }

    override fun display(url: String?, imageView: ImageView?,
                         options: NDisplayOptions?,
                         progressListener: NImageLoadingProgressListener?) {
        display(url, imageView, options, null, progressListener)
    }

    override fun display(url: String?, imageView: ImageView?,
                         options: NDisplayOptions?, listener: NImageLoadingListener?,
                         progressListener: NImageLoadingProgressListener?) {

        if (imageView == null || imageView !is SimpleDraweeView) return

        if (options != null) {

            val oldHierarchy = imageView.hierarchy
            oldHierarchy.fadeDuration = 300

            if (!options.isNoImageWhileLoading && options.imageOnLoading != -1)
                oldHierarchy.setPlaceholderImage(options.imageOnLoading)

            if (options.imageOnFail != -1) {
                oldHierarchy.setFailureImage(options.imageOnFail)
            }

            imageView.hierarchy = oldHierarchy
        }

        val controller = Fresco.newDraweeControllerBuilder()
                .setUri(Uri.parse(url))
                .setAutoPlayAnimations(true)
                .build()

        imageView.controller = controller
    }

    override fun display(uri: Uri?, imageView: ImageView?) {
        display(uri, imageView, null, null, null)
    }

    override fun display(uri: Uri?, imageView: ImageView?,
                         options: NDisplayOptions?) {
        display(uri, imageView, options, null, null)
    }

    override fun display(uri: Uri?, imageView: ImageView?,
                         listener: NImageLoadingListener?) {
        display(uri, imageView, null, listener, null)
    }

    override fun display(uri: Uri?, imageView: ImageView?,
                         progressListener: NImageLoadingProgressListener?) {
        display(uri, imageView, null, null, progressListener)
    }

    override fun display(uri: Uri, imageView: ImageView,
                         listener: NImageLoadingListener,
                         progressListener: NImageLoadingProgressListener) {
        display(uri, imageView, null, listener, progressListener)
    }

    override fun display(uri: Uri?, imageView: ImageView?, options: NDisplayOptions?,
                         listener: NImageLoadingListener?) {
        display(uri, imageView, options, listener, null)
    }

    override fun display(uri: Uri?, imageView: ImageView?, options: NDisplayOptions?,
                         progressListener: NImageLoadingProgressListener?) {
        display(uri, imageView, options, null, progressListener)
    }

    override fun display(uri: Uri?, imageView: ImageView?, options: NDisplayOptions?,
                         listener: NImageLoadingListener?,
                         progressListener: NImageLoadingProgressListener?) {
        display(uri.toString(), imageView, options, listener, progressListener)
    }

    override fun display(resourceId: Int?, imageView: ImageView?) {
        display(resourceId, imageView, null, null, null)
    }

    override fun display(resourceId: Int?, imageView: ImageView?,
                         listener: NImageLoadingListener?) {
        display(resourceId, imageView, null, listener, null)
    }

    override fun display(resourceId: Int?, imageView: ImageView?,
                         progressListener: NImageLoadingProgressListener?) {
        display(resourceId, imageView, null, null, progressListener)
    }

    override fun display(resourceId: Int?, imageView: ImageView?,
                         options: NDisplayOptions?) {
        display(resourceId, imageView, options, null, null)
    }

    override fun display(resourceId: Int?, imageView: ImageView?,
                         options: NDisplayOptions?, listener: NImageLoadingListener?) {
        display(resourceId, imageView, options, listener, null)
    }

    override fun display(resourceId: Int?, imageView: ImageView?,
                         options: NDisplayOptions?,
                         progressListener: NImageLoadingProgressListener?) {
        display(resourceId, imageView, options, null, progressListener)
    }

    override fun display(resourceId: Int?, imageView: ImageView?,
                         listener: NImageLoadingListener?,
                         progressListener: NImageLoadingProgressListener?) {
        display(resourceId, imageView, null, listener, progressListener)
    }

    override fun display(resourceId: Int?, imageView: ImageView?,
                         options: NDisplayOptions?, listener: NImageLoadingListener?,
                         progressListener: NImageLoadingProgressListener?) {
        display("drawable://" + resourceId, imageView, options, listener,
                progressListener)
    }

    override fun display(file: File?, imageView: ImageView?) {
        display(file, imageView, null, null, null)
    }

    override fun display(file: File?, imageView: ImageView?,
                         progressListener: NImageLoadingProgressListener?) {
        display(file, imageView, null, null, progressListener)
    }

    override fun display(file: File?, imageView: ImageView?,
                         listener: NImageLoadingListener?) {
        display(file, imageView, null, listener, null)
    }

    override fun display(file: File?, imageView: ImageView?,
                         listener: NImageLoadingListener?,
                         progressListener: NImageLoadingProgressListener?) {
        display(file, imageView, null, listener, progressListener)
    }

    override fun display(file: File?, imageView: ImageView?, options: NDisplayOptions?) {
        display(file, imageView, options, null, null)
    }

    override fun display(file: File?, imageView: ImageView?,
                         options: NDisplayOptions?,
                         progressListener: NImageLoadingProgressListener?) {
        display(file, imageView, options, null, progressListener)
    }

    override fun display(file: File?, imageView: ImageView?,
                         options: NDisplayOptions?, listener: NImageLoadingListener?) {
        display(file, imageView, options, listener, null)
    }

    override fun display(file: File?, imageView: ImageView?,
                         options: NDisplayOptions?, listener: NImageLoadingListener?,
                         progressListener: NImageLoadingProgressListener?) {
        display(Uri.fromFile(file), imageView, options, listener,
                progressListener)
    }

    override fun loadImage(url: String, listener: NImageLoadingListener) {
        loadImage(url, listener, null)
    }

    override fun loadImage(url: String, listener: NImageLoadingListener,
                           widthSize: Int, heightSize: Int) {
        loadImage(url, listener, null, widthSize, heightSize)
    }

    override fun loadImage(url: String,
                           progressListener: NImageLoadingProgressListener) {
        loadImage(url, null, progressListener)
    }

    override fun loadImage(url: String,
                           progressListener: NImageLoadingProgressListener, widthSize: Int,
                           heightSize: Int) {
        loadImage(url, null, progressListener, widthSize, heightSize)
    }

    override fun loadImage(url: String,
                           listener: NImageLoadingListener?,
                           progressListener: NImageLoadingProgressListener?) {
//        val impl = LoadingAndProgressListenerImpl(
//                listener, progressListener)
        //TODO try to use listeners
        val request = ImageRequest.fromUri(url)
        Fresco.getImagePipeline().prefetchToDiskCache(request, mContext)
    }

    override fun loadImage(url: String,
                           listener: NImageLoadingListener?,
                           progressListener: NImageLoadingProgressListener?,
                           widthSize: Int,
                           heightSize: Int) {
//        val impl = LoadingAndProgressListenerImpl(
//                listener, progressListener)

        //TODO try to use listeners
        val request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(url))
                .setResizeOptions(ResizeOptions(widthSize, heightSize))
                .build()
        Fresco.getImagePipeline().prefetchToDiskCache(request, mContext)
    }

    private class LoadingAndProgressListenerImpl {
        internal var loadingListener: NImageLoadingListener?
        internal var progressListener: NImageLoadingProgressListener?

        constructor(
                loadingListener: NImageLoadingListener,
                progressListener: NImageLoadingProgressListener) {
            this.loadingListener = loadingListener
            this.progressListener = progressListener
        }
    }
}
