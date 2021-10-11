package co.core.preference

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by freesky1102 on 1/23/18.
 */

class AppPreference(context: Context, sharedPrefFileName: String) : CustomPreference {

    private val mPreferences: SharedPreferences = context.getSharedPreferences(sharedPrefFileName,
            Context.MODE_PRIVATE)

    private val editor: SharedPreferences.Editor
        get() = mPreferences.edit()

    override fun putString(key: String, value: String) {
        editor.putString(key, value).apply()
    }

    override fun putInt(key: String, value: Int) {
        editor.putInt(key, value).apply()
    }

    override fun putBoolean(key: String, value: Boolean) {
        editor.putBoolean(key, value).apply()
    }

    override fun getString(key: String, defaultValue: String): String {
        return mPreferences.getString(key, defaultValue)!!
    }

    override fun getInt(key: String, defaultValue: Int): Int {
        return mPreferences.getInt(key, defaultValue)
    }

    override fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return mPreferences.getBoolean(key, defaultValue)
    }

    override fun putLong(key: String, value: Long) {
        editor.putLong(key, value).apply()
    }

    override fun getLong(key: String, defaultValue: Long): Long {
        return mPreferences.getLong(key, defaultValue)
    }

    override fun putMutableSet(key: String, set: MutableSet<String>) {
        editor.putStringSet(key, set).apply()
    }

    override fun getMutableSet(key: String): MutableSet<String>? {
        return mPreferences.getStringSet(key, mutableSetOf<String>())
    }

    override fun clearAll() {
        mPreferences.edit().clear().apply()
    }
}


