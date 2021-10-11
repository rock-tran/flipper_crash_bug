package co.core.imageloader

import android.net.Uri
import android.widget.ImageView

import java.io.File

/**
 * @author TUNGDX
 */

/**
 * Cac function load va hien thi anh<br></br>
 * <br></br>
 * - Day la mot interface chung cho nhieu ung dung, nen chi dinh nghia nhung
 * function chung nhat, neu ung dung nao muon them cac function ve hien thi thi
 * them truc tiep vao interface nay hoac muon custom viec hien thi hon nua thi
 * su dung class [NDisplayOptions] de custom.<br></br>
 * <br></br>
 * Vi du: Mac dinh cac man hinh deu co 1 anh default trong luc loading nhung lai
 * co man hinh nao do khong co anh default nay ma hien thi trang. => Khi nay cho
 * gia tri do vao class [NDisplayOptions] de dieu khien.
 */
interface NDisplayImageLoader {
    /**
     * Load image from internet and display to view

     * @param url       address of image
     * *
     * @param imageView
     */
    fun display(url: String?, imageView: ImageView?)

    /**
     * Load image from internet and display to view

     * @param url       address of image
     * *
     * @param imageView
     * *
     * @param options   Custom display image to view
     */
    fun display(url: String?, imageView: ImageView?, options: NDisplayOptions?)

    /**
     * Load image from internet and display to view

     * @param url       address of image
     * *
     * @param imageView
     * *
     * @param listener  callback after image loaded (error or success)
     */
    fun display(url: String?, imageView: ImageView?,
                listener: NImageLoadingListener?)

    /**
     * Load image from internet and display to view

     * @param url       address of image
     * *
     * @param imageView
     * *
     * @param options   Custom display image to view
     * *
     * @param listener  callback after image loaded (error or success)
     */
    fun display(url: String?, imageView: ImageView?,
                options: NDisplayOptions?, listener: NImageLoadingListener?)

    /**
     * Load image from internet and display to view

     * @param url              address of image
     * *
     * @param imageView
     * *
     * @param progressListener progress while loading image
     */
    fun display(url: String?, imageView: ImageView?,
                progressListener: NImageLoadingProgressListener?)

    /**
     * Load image from internet and display to view

     * @param url              address of image
     * *
     * @param imageView
     * *
     * @param options          Custom display image to view
     * *
     * @param progressListener progress while loading image
     */
    fun display(url: String?, imageView: ImageView?,
                options: NDisplayOptions?,
                progressListener: NImageLoadingProgressListener?)

    /**
     * Load image from internet and display to view

     * @param url              address of image
     * *
     * @param imageView
     * *
     * @param listener         callback after image loaded (error or success)
     * *
     * @param progressListener progress while loading image
     */
    fun display(url: String?, imageView: ImageView?,
                listener: NImageLoadingListener?,
                progressListener: NImageLoadingProgressListener?)

    /**
     * Load image from internet and display to view

     * @param url              address of image
     * *
     * @param imageView
     * *
     * @param options          Custom display image to view
     * *
     * @param listener         callback after image loaded (error or success)
     * *
     * @param progressListener progress while loading image
     */
    fun display(url: String?, imageView: ImageView?,
                options: NDisplayOptions?, listener: NImageLoadingListener?,
                progressListener: NImageLoadingProgressListener?)

    /**
     * Load image from uri and display to view

     * @param uri       uri of image. uri of photo, video, asset, contact (Ex:
     * *                  content://, assets://, file:///)
     * *
     * @param imageView
     */
    fun display(uri: Uri?, imageView: ImageView?)

    /**
     * Load image from uri and display to view

     * @param uri       uri of image. uri of photo, video, asset, contact (Ex:
     * *                  content://, assets://, file:///)
     * *
     * @param imageView
     * *
     * @param options   Custom display image to view
     */
    fun display(uri: Uri?, imageView: ImageView?, options: NDisplayOptions?)

    /**
     * Load image from uri and display to view

     * @param uri       uri of image. uri of photo, video, asset, contact (Ex:
     * *                  content://, assets://, file:///)
     * *
     * @param imageView
     * *
     * @param listener  callback after image loaded (error or success)
     */
    fun display(uri: Uri?, imageView: ImageView?,
                listener: NImageLoadingListener?)

    /**
     * Load image from uri and display to view

     * @param uri       uri of image. uri of photo, video, asset, contact (Ex:
     * *                  content://, assets://, file:///)
     * *
     * @param imageView
     * *
     * @param options   Custom display image to view
     * *
     * @param listener  callback after image loaded (error or success)
     */
    fun display(uri: Uri?, imageView: ImageView?, options: NDisplayOptions?,
                listener: NImageLoadingListener?)

    /**
     * Load image from uri and display to view

     * @param uri              uri of image. uri of photo, video, asset, contact (Ex:
     * *                         content://, assets://, file:///)
     * *
     * @param imageView
     * *
     * @param progressListener progress while loading image
     */
    fun display(uri: Uri?, imageView: ImageView?,
                progressListener: NImageLoadingProgressListener?)

    /**
     * Load image from uri and display to view

     * @param uri              uri of image. uri of photo, video, asset, contact (Ex:
     * *                         content://, assets://, file:///)
     * *
     * @param imageView
     * *
     * @param options          Custom display image to view
     * *
     * @param progressListener progress while loading image
     */
    fun display(uri: Uri?, imageView: ImageView?, options: NDisplayOptions?,
                progressListener: NImageLoadingProgressListener?)

    /**
     * Load image from uri and display to view

     * @param uri              uri of image. uri of photo, video, asset, contact (Ex:
     * *                         content://, assets://, file:///)
     * *
     * @param imageView
     * *
     * @param listener         callback after image loaded (error or success)
     * *
     * @param progressListener progress while loading image
     */
    fun display(uri: Uri, imageView: ImageView,
                listener: NImageLoadingListener,
                progressListener: NImageLoadingProgressListener)

    /**
     * Load image from uri and display to view

     * @param uri              uri of image. uri of photo, video, asset, contact (Ex:
     * *                         content://, assets://, file:///)
     * *
     * @param imageView
     * *
     * @param options          Custom display image to view
     * *
     * @param listener         callback after image loaded (error or success)
     * *
     * @param progressListener progress while loading image
     */
    fun display(uri: Uri?, imageView: ImageView?, options: NDisplayOptions?,
                listener: NImageLoadingListener?,
                progressListener: NImageLoadingProgressListener?)

    /**
     * Load image from resourceId and display to view

     * @param resourceId resource in drawable folder
     * *
     * @param imageView
     */
    fun display(resourceId: Int?, imageView: ImageView?)

    /**
     * Load image from resourceId and display to view

     * @param resourceId resource in drawable folder
     * *
     * @param imageView
     * *
     * @param options    Custom display image to view
     */
    fun display(resourceId: Int?, imageView: ImageView?,
                options: NDisplayOptions?)

    /**
     * Load image from resourceId and display to view

     * @param resourceId resource in drawable folder
     * *
     * @param imageView
     * *
     * @param listener   callback after image loaded (error or success)
     */
    fun display(resourceId: Int?, imageView: ImageView?,
                listener: NImageLoadingListener?)

    /**
     * Load image from resourceId and display to view

     * @param resourceId resource in drawable folder
     * *
     * @param imageView
     * *
     * @param options    Custom display image to view
     * *
     * @param listener   callback after image loaded (error or success)
     */
    fun display(resourceId: Int?, imageView: ImageView?,
                options: NDisplayOptions?, listener: NImageLoadingListener?)

    /**
     * Load image from resourceId and display to view

     * @param resourceId       resource in drawable folder
     * *
     * @param imageView
     * *
     * @param progressListener progress while loading image
     */
    fun display(resourceId: Int?, imageView: ImageView?,
                progressListener: NImageLoadingProgressListener?)

    /**
     * Load image from resourceId and display to view

     * @param resourceId       resource in drawable folder
     * *
     * @param imageView
     * *
     * @param options          Custom display image to view
     * *
     * @param progressListener progress while loading image
     */
    fun display(resourceId: Int?, imageView: ImageView?,
                options: NDisplayOptions?,
                progressListener: NImageLoadingProgressListener?)

    /**
     * Load image from resourceId and display to view

     * @param resourceId       resource in drawable folder
     * *
     * @param imageView
     * *
     * @param listener         callback after image loaded (error or success)
     * *
     * @param progressListener progress while loading image
     */
    fun display(resourceId: Int?, imageView: ImageView?,
                listener: NImageLoadingListener?,
                progressListener: NImageLoadingProgressListener?)

    /**
     * Load image from resourceId and display to view

     * @param resourceId       resource in drawable folder
     * *
     * @param imageView
     * *
     * @param options          Custom display image to view
     * *
     * @param listener         callback after image loaded (error or success)
     * *
     * @param progressListener progress while loading image
     */
    fun display(resourceId: Int?, imageView: ImageView?,
                options: NDisplayOptions?, listener: NImageLoadingListener?,
                progressListener: NImageLoadingProgressListener?)

    /**
     * Load image from file and display to view

     * @param file      file is photo, not support if other (ex: video's not
     * *                  supported, use
     * *                  [NDisplayImageLoader.display]
     * *                  instead)
     * *
     * @param imageView
     */
    fun display(file: File?, imageView: ImageView?)

    /**
     * Load image from file and display to view

     * @param file      file is photo, not support if other (ex: video's not
     * *                  supported, use
     * *                  [NDisplayImageLoader.display]
     * *                  instead)
     * *
     * @param imageView
     * *
     * @param options   Custom display image to view
     */
    fun display(file: File?, imageView: ImageView?, options: NDisplayOptions?)

    /**
     * Load image from file and display to view

     * @param file      file is photo, not support if other (ex: video's not
     * *                  supported, use
     * *                  [NDisplayImageLoader.display]
     * *                  instead)
     * *
     * @param imageView
     * *
     * @param listener  callback after image loaded (error or success)
     */
    fun display(file: File?, imageView: ImageView?,
                listener: NImageLoadingListener?)

    /**
     * Load image from file and display to view

     * @param file      file is photo, not support if other (ex: video's not
     * *                  supported, use
     * *                  [NDisplayImageLoader.display]
     * *                  instead)
     * *
     * @param imageView
     * *
     * @param options   Custom display image to view
     * *
     * @param listener  callback after image loaded (error or success)
     */
    fun display(file: File?, imageView: ImageView?, options: NDisplayOptions?,
                listener: NImageLoadingListener?)

    /**
     * Load image from file and display to view

     * @param file             file is photo, not support if other (ex: video's not
     * *                         supported, use
     * *                         [NDisplayImageLoader.display]
     * *                         instead)
     * *
     * @param imageView
     * *
     * @param progressListener progress while loading image
     */
    fun display(file: File?, imageView: ImageView?,
                progressListener: NImageLoadingProgressListener?)

    /**
     * Load image from file and display to view

     * @param file             file is photo, not support if other (ex: video's not
     * *                         supported, use
     * *                         [NDisplayImageLoader.display]
     * *                         instead)
     * *
     * @param imageView
     * *
     * @param options          Custom display image to view
     * *
     * @param progressListener progress while loading image
     */
    fun display(file: File?, imageView: ImageView?, options: NDisplayOptions?,
                progressListener: NImageLoadingProgressListener?)

    /**
     * Load image from file and display to view

     * @param file             file is photo, not support if other (ex: video's not
     * *                         supported, use
     * *                         [NDisplayImageLoader.display]
     * *                         instead)
     * *
     * @param imageView
     * *
     * @param listener         callback after image loaded (error or success)
     * *
     * @param progressListener progress while loading image
     */
    fun display(file: File?, imageView: ImageView?,
                listener: NImageLoadingListener?,
                progressListener: NImageLoadingProgressListener?)

    /**
     * Load image from file and display to view

     * @param file             file is photo, not support if other (ex: video's not
     * *                         supported, use
     * *                         [NDisplayImageLoader.display]
     * *                         instead)
     * *
     * @param imageView
     * *
     * @param options          Custom display image to view
     * *
     * @param listener         callback after image loaded (error or success)
     * *
     * @param progressListener progress while loading image
     */
    fun display(file: File?, imageView: ImageView?, options: NDisplayOptions?,
                listener: NImageLoadingListener?,
                progressListener: NImageLoadingProgressListener?)

}
