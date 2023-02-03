package com.route.todoappc_37.ui.Prefrences

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.route.todoappc_37.ui.Constants


class PreferenceManager(val context: Context) {

    fun setLanguage(pos: Int) {
        val sharedPref = context.getSharedPreferences(Constants.PREFERENCE_NAME, MODE_PRIVATE)
        sharedPref.edit().putInt(Constants.language, pos).commit()
    }

    fun getLanguage(): Int {
        val sharedPref = context.getSharedPreferences(Constants.PREFERENCE_NAME, MODE_PRIVATE)
        return sharedPref.getInt(Constants.language, 0)
    }
    fun setMode(pos: Int) {
        val sharedPref = context.getSharedPreferences(Constants.PREFERENCE_NAME, MODE_PRIVATE)
        sharedPref.edit().putInt(Constants.mode, pos).commit()
    }

    fun getMode(): Int {
        val sharedPref = context.getSharedPreferences(Constants.PREFERENCE_NAME, MODE_PRIVATE)
        return sharedPref.getInt(Constants.mode, 0)

}

}