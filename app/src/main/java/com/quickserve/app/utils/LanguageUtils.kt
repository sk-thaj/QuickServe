package com.quickserve.app.utils

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.res.Configuration
import java.util.Locale

object LanguageUtils {
    @Suppress("DEPRECATION")
    fun setLocale(context: Context, language: String) {
        val localeCode = when (language) {
            "Hindi" -> "hi"
            "Telugu" -> "te"
            "Tamil" -> "ta"
            "Marathi" -> "mr"
            "Malayalam" -> "ml"
            else -> "en"
        }
        val locale = Locale(localeCode)
        Locale.setDefault(locale)
        val config = Configuration(context.resources.configuration)
        config.setLocale(locale)
        context.resources.updateConfiguration(config, context.resources.displayMetrics)
        
        context.findActivity()?.recreate()
    }

    private fun Context.findActivity(): Activity? = when (this) {
        is Activity -> this
        is ContextWrapper -> baseContext.findActivity()
        else -> null
    }
}
