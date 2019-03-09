package com.calestu.squadscbfa.data.local.prefs

import android.content.Context
import com.calestu.squadscbfa.util.ext.formatToLocalDate
import java.util.*

class AppPreferencesBack(context: Context) : Preferences(context) {

    var lastDateCheckTaskCompleted: String? by Preference()

    fun edit(appPreferences: AppPreferencesBack.() -> Unit) {
        appPreferences()
        editor.apply()
    }

    fun setLastDateCheckTaskCompletedCurrentDate() {
        this@AppPreferencesBack.edit {
            lastDateCheckTaskCompleted = Date().formatToLocalDate()
        }
    }

}