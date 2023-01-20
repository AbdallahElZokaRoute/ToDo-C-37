package com.route.todoappc_37

import android.app.Application
import android.content.Context
import com.route.todoappc_37.ui.Prefrences.PreferenceManager
import com.route.todoappc_37.ui.fragments.SettingsFragment

import com.yariksoffice.lingver.Lingver



class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Lingver.init(this,"en")
        val context : Context = applicationContext
        val handlers = Handlers(PreferenceManager(context))
        handlers.handleMode()


    }

}