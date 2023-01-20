package com.route.todoappc_37

import android.app.Application

import com.yariksoffice.lingver.Lingver

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Lingver.init(this,"en")

    }

}