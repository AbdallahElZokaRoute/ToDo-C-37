package com.route.todoappc_37.ui

import android.content.Context
import android.content.SharedPreferences

object LocalStorageUtils {
    var INSTANCE: SharedPreferences? = null

    //Singleton Pattern
    fun getInstance(context: Context): SharedPreferences {
        if (INSTANCE == null) {
            INSTANCE = getSharedPreference(context)
        }
        return INSTANCE!!

    }

    private fun getSharedPreference(context: Context): SharedPreferences {
        return context.getSharedPreferences(Constants.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
    }
}