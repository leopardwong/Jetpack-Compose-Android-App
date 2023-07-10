package com.example.jetpack_compose_sample_app.helper

import android.content.Context
import android.content.SharedPreferences

class SharedPreferenceHelper(val context: Context) {
    val sharedPref: SharedPreferences =
        context.getSharedPreferences("AppCore", Context.MODE_PRIVATE)

    fun save(KEY_NAME: String, text: String): Boolean {

        val editor: SharedPreferences.Editor = sharedPref.edit()

        editor.putString(KEY_NAME, text)

        return editor.commit()
    }

    fun save(KEY_NAME: String, value: Int): Boolean {
        val editor: SharedPreferences.Editor = sharedPref.edit()

        editor.putInt(KEY_NAME, value)

        return editor.commit()
    }

    fun save(KEY_NAME: String, status: Boolean): Boolean {

        val editor: SharedPreferences.Editor = sharedPref.edit()

        editor.putBoolean(KEY_NAME, status)

        return editor.commit()
    }

    fun getValueString(KEY_NAME: String): String? {
        return sharedPref.getString(KEY_NAME, "")
    }

    fun getValueInt(KEY_NAME: String): Int {
        return sharedPref.getInt(KEY_NAME, 0)
    }

    fun getValueBoolean(KEY_NAME: String, defaultValue: Boolean): Boolean {
        return sharedPref.getBoolean(KEY_NAME, defaultValue)
    }

    fun containsKey(KEY_NAME: String): Boolean {
        return sharedPref.contains(KEY_NAME)
    }

    fun clearSharedPreference() {

        val editor: SharedPreferences.Editor = sharedPref.edit()

        //sharedPref = PreferenceManager.getDefaultSharedPreferences(context);

        editor.clear()
        editor.commit()
    }

    fun removeValue(KEY_NAME: String) {

        val editor: SharedPreferences.Editor = sharedPref.edit()

        editor.remove(KEY_NAME)
        editor.commit()
    }

    private val TAG = SharedPreferenceHelper::class.java.simpleName

    companion object {
        const val TAG_AKAMAI_SALT = "TAG_AKAMAI_SALT"
    }
}