package co.core.imageloader

/**
 * @author TUNGDX
 */

import androidx.annotation.DrawableRes

/**
 * Custom display image to view (ex: default image for loading or animation...)
 */
class NDisplayOptions private constructor(builder: NDisplayOptions.Builder) {
    val isNoImageWhileLoading: Boolean

    val isCheckActualViewSize: Boolean
    @get:DrawableRes
    val imageOnLoading: Int
    @get:DrawableRes
    val imageOnFail: Int
    val isCacheOnDisk: Boolean

    init {
        isNoImageWhileLoading = builder.isNoImageWhileLoading
        isCheckActualViewSize = builder.checkActualViewSize
        imageOnLoading = builder.imageOnLoading
        imageOnFail = builder.imageOnFail
        isCacheOnDisk = builder.isCacheOnDisk
    }

    class Builder {
        internal var isNoImageWhileLoading: Boolean = false
        internal var checkActualViewSize: Boolean = false
        internal var imageOnLoading = -1
        internal var imageOnFail = -1
        internal var isCacheOnDisk = true

        fun setImageOnFail(@DrawableRes resourceId: Int): Builder {
            this.imageOnFail = resourceId
            return this
        }

        fun setImageOnLoading(@DrawableRes resourceId: Int): Builder {
            this.imageOnLoading = resourceId
            return this
        }

        fun setNoImageWhileLoading(isNoImage: Boolean): Builder {
            isNoImageWhileLoading = isNoImage
            return this
        }

        fun setCheckActualViewSize(isCheck: Boolean): Builder {
            checkActualViewSize = isCheck
            return this
        }

        /**
         * @param isCache Default is true.
         * *
         * @return
         */
        fun cacheOnDisk(isCache: Boolean): Builder {
            isCacheOnDisk = isCache
            return this
        }

        fun build(): NDisplayOptions {
            return NDisplayOptions(this)
        }
    }
}
