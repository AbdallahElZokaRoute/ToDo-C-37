package com.route.todoappc_37

import androidx.appcompat.app.AppCompatDelegate
import com.route.todoappc_37.ui.Prefrences.PreferenceManager




class Handlers(var preferenceManger: PreferenceManager) {



    fun handleMode(){

        if (preferenceManger.getMode()==0){


            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
        else if (preferenceManger.getMode()==1){


            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }



    }

}