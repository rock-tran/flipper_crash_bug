package co.core.imageloader

/**
 * @author TUNGDX
 */

/**
 * Tong hop nhung function can co cua mot thu vien load anh duoc su dung trong
 * ung dung.<br></br>
 * Nhung function can co nhu sau  * Load va hien thi anh tu internet (http)
 *  * Load va hien thi anh tu mot Uri (Uri co the la: photo, video, asset,
 * contact)  * Load va hien thi anh tu file  *
 * Load va hien thi anh tu resource (Anh o cac thu muc drawable) <br></br>
 * <br></br>
 * - Trong cac ung dung se dung truc tiep interface nay ma khong su dung cu the
 * mot thu vien load anh nao (lam viec nay de viec thay doi thu vien load anh de
 * dang, khong gap nhieu kho khan). <br></br>
 * - Khi muon dung thu vien load anh nao vao ung dung thi thu vien do phai
 * implements interface nay. Da su dung thu vien [UIL](https://github.com/nostra13/Android-Universal-Image-Loader) de
 * lam thu vien implements mac dinh cho interface nay, ung dung nao muon su dung
 * thu vien khac thi dev nghien cuu thu vien do va implements.<br></br>
 * - Interface nay rat chung cho nhieu ung dung nen trong ung nao nao can them
 * function moi thi nghien cuu va them vao interface nay.
 */
interface NImageLoader : NDisplayImageLoader {
    /**
     * Load image from internet (only load)

     * @param url      address of image
     * *
     * @param listener callback after image loaded (error or success)
     */
    fun loadImage(url: String, listener: NImageLoadingListener)

    /**
     * Load image from internet (only load)

     * @param url        address of image
     * *
     * @param listener   callback after image loaded (error or success)
     * *
     * @param widthSize  widthSize desire of image after loaded
     * *
     * @param heightSize heightSize desire of image after loaded
     */
    fun loadImage(url: String, listener: NImageLoadingListener,
                  widthSize: Int, heightSize: Int)

    /**
     * Load image from internet (only load)

     * @param url              address of image
     * *
     * @param progressListener progress while loading image
     */
    fun loadImage(url: String,
                  progressListener: NImageLoadingProgressListener)

    /**
     * Load image from internet (only load)

     * @param url              address of image
     * *
     * @param progressListener progress while loading image
     * *
     * @param widthSize        widthSize desire of image after loaded
     * *
     * @param heightSize       heightSize desire of image after loaded
     */
    fun loadImage(url: String,
                  progressListener: NImageLoadingProgressListener,
                  widthSize: Int,
                  heightSize: Int)

    /**
     * Load image from internet (only load)

     * @param url              address of image
     * *
     * @param listener         callback after image loaded (error or success)
     * *
     * @param progressListener progress while loading image
     */
    fun loadImage(url: String,
                  listener: NImageLoadingListener?,
                  progressListener: NImageLoadingProgressListener?)

    /**
     * Load image from internet (only load)

     * @param url              address of image
     * *
     * @param listener         callback after image loaded (error or success)
     * *
     * @param progressListener progress while loading image
     * *
     * @param widthSize        widthSize desire of image after loaded
     * *
     * @param heightSize       heightSize desire of image after loaded
     */
    fun loadImage(url: String, listener: NImageLoadingListener?,
                  progressListener: NImageLoadingProgressListener?, widthSize: Int,
                  heightSize: Int)
}
