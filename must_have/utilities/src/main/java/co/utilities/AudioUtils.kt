package co.utilities

import android.media.MediaPlayer
import android.text.TextUtils

/**
 * @author TUNGDX
 */

/**
 * All utils for Audio.
 */
object AudioUtils {

    /**
     * Get duration of audio.

     * @param path Path of audio.
     * *
     * @return duration of auido. 0 if path is empty.
     */
    fun getDuration(path: String): Long {
        var result: Long = 0
        if (!TextUtils.isEmpty(path)) {
            try {
                val mMediaPlayer: MediaPlayer? = MediaPlayer()
                mMediaPlayer!!.setDataSource(path)
                mMediaPlayer.prepare()
                result = mMediaPlayer.duration.toLong()
                if (mMediaPlayer.isPlaying)
                    mMediaPlayer.pause()
                mMediaPlayer.stop()
                mMediaPlayer.reset()
                mMediaPlayer.release()
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
        return result
    }
}
