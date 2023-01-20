package com.route.todoappc_37.ui.Prefrences

import android.content.Context
import android.content.Context.MODE_PRIVATE

const val PREFERENCE_NAME = "test"
const val language = "language"
const val mode = "mode"

class PreferenceManager(val context: Context) {

    fun setSelection(pos: Int) {
        val sharedPref = context.getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE)
        sharedPref.edit().putInt(language, pos).commit()
    }

    fun getSelection(): Int {
        val sharedPref = context.getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE)
        return sharedPref.getInt(language, 0)
    }
    fun setMode(pos: Int) {
        val sharedPref = context.getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE)
        sharedPref.edit().putInt(mode, pos).commit()
    }

    fun getMode(): Int {
        val sharedPref = context.getSharedPreferences(PREFERENCE_NAME, MODE_PRIVATE)
        return sharedPref.getInt(mode, 0)

}

}