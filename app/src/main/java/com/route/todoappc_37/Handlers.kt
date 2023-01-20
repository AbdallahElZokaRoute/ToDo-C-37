package com.route.todoappc_37

import android.content.Context

import androidx.appcompat.app.AppCompatDelegate
import com.route.todoappc_37.ui.Prefrences.PreferenceManager
import com.yariksoffice.lingver.Lingver



class Handlers(var preferenceManger: PreferenceManager) {



    fun handleMode(){

        if (preferenceManger.getMode()==0){


            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
        else if (preferenceManger.getMode()==1){

            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

        }


    }


    fun handleLanguage(context : Context) {
        if (preferenceManger.getSelection() == 0) {

            Lingver.getInstance().setLocale(context, "en")


        } else if (preferenceManger.getSelection() == 1) {

            Lingver.getInstance().setLocale(context, "ar")


        }
    }

}