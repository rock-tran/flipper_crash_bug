package co.utilities

import android.content.ContentProvider
import android.content.ContentResolver
import android.content.Context
import android.database.Cursor
import android.media.MediaPlayer
import android.net.Uri
import android.provider.MediaStore

/**
 * @author TUNGDX
 */

/**
 * All utils for videos.
 */
object VideoUtils {

    /**
     * Get video's duration from [ContentProvider]

     * @param context
     * *
     * @param uri     must has [Uri.getScheme] equals
     * *                [ContentResolver.SCHEME_CONTENT]
     * *
     * @return Duration of video, in milliseconds.
     */
    fun getDuration(context: Context, uri: Uri): Long {
        var duration = 0L
        val cursor = MediaStore.Video.query(context.contentResolver,
                uri, arrayOf(MediaStore.Video.VideoColumns.DURATION))
        if (cursor != null) {
            cursor.moveToFirst()
            duration = cursor.getLong(cursor
                    .getColumnIndex(MediaStore.Video.VideoColumns.DURATION))
            cursor.close()
        }
        return duration
    }

    /**
     * Get video's duration without [ContentProvider]. Because not know
     * [Uri] of video.

     * @param context
     * *
     * @param path    Path of video file.
     * *
     * @return Duration of video, in milliseconds. Return 0 if path is null.
     */
    fun getDuration(context: Context, path: String): Long {
        var mMediaPlayer: MediaPlayer? = null
        var duration: Long = 0
        try {
            mMediaPlayer = MediaPlayer()
            mMediaPlayer.setDataSource(context, Uri.parse(path))
            mMediaPlayer.prepare()
            duration = mMediaPlayer.duration.toLong()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            if (mMediaPlayer != null) {
                mMediaPlayer.reset()
                mMediaPlayer.release()
            }
        }
        return duration
    }

    /**
     * Get path of video from uri

     * @param contentResolver
     * *
     * @param contentURI
     * *
     * @return path of video. Null if not found.
     */
    fun getRealPathFromURI(contentResolver: ContentResolver,
                           contentURI: Uri): String? {
        val cursor = contentResolver.query(contentURI, null, null, null, null)

        var path: String?

        if (cursor == null)
            path = contentURI.path
        else {
            cursor.moveToFirst()
            val idx = cursor.getColumnIndex(MediaStore.Video.VideoColumns.DATA)
            try {
                path = cursor.getString(idx)
            } catch (exception: Exception) {
                path = null
            }

            cursor.close()
        }

        return path
    }

}
