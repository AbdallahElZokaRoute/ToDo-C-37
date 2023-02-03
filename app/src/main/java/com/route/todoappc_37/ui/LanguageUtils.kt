package com.route.todoappc_37.ui

import android.content.Context
import java.util.Locale

object LanguageUtils {

    fun setLocate(context : Context, language: String) : Context{
        val locale = Locale(language)
        val resources = context.resources
        val configuration = resources.configuration
        configuration.setLocale(locale)
        return context.createConfigurationContext(configuration)
    }


}