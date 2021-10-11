package co.core.preference

/**
 * Created by freesky1102 on 1/23/18.
 */

interface CustomPreference {

    fun putString(key: String, value: String)

    fun putInt(key: String, value: Int)

    fun putBoolean(key: String, value: Boolean)

    fun putLong(key: String, value: Long)

    fun getString(key: String, defaultValue: String): String

    fun getInt(key: String, defaultValue: Int): Int

    fun getBoolean(key: String, defaultValue: Boolean): Boolean

    fun getLong(key: String, defaultValue: Long): Long

    fun putMutableSet(key: String, set: MutableSet<String>)

    fun getMutableSet(key: String): MutableSet<String>?

    fun clearAll()

}
