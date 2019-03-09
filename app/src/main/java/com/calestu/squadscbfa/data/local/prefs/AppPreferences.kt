package com.calestu.squadscbfa.data.local.prefs

import android.content.Context
import android.content.SharedPreferences
import com.calestu.squadscbfa.data.local.prefs.PreferenceHelper.get
import com.calestu.squadscbfa.data.local.prefs.PreferenceHelper.set
import com.calestu.squadscbfa.util.ext.formatToLocalDate
import timber.log.Timber
import java.util.*
import javax.inject.Inject

class AppPreferences @Inject constructor(
    private val context: Context,
    private val preferencesHelper: SharedPreferences
) {

    fun setLastDateCheckTaskCompletedCurrentDate() {
        Timber.d("setLastDateCheckTaskCompletedCurrentDate")
        preferencesHelper[PrefsConstants.PREF_LAST_DATE_CHECK_TASK_COMPLETED] = Date().formatToLocalDate()
    }

    fun getLastDateCheckTaskCompletedCurrentDate(): String? {
        return preferencesHelper[PrefsConstants.PREF_LAST_DATE_CHECK_TASK_COMPLETED]
    }

}