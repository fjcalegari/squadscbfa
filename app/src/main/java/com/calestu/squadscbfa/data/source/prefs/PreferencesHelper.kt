package com.calestu.squadscbfa.data.source.prefs

import android.content.Context
import android.preference.PreferenceManager

class PreferencesHelper(
    context: Context
) {

    companion object {
        private const val CURRENT_ROUND = "CURRENT_ROUND"
        private const val CURRENT_FORMATION = "CURRENT_FORMATION"
    }

    private val preferences = PreferenceManager.getDefaultSharedPreferences(context)

    var currentRound : Int
        get() {
            val index = preferences.getInt(CURRENT_ROUND, 0)
            return index
        }
        set(value) = preferences.edit().putInt(CURRENT_ROUND,value).apply()

    var currentFormation = preferences.getInt(CURRENT_FORMATION, 0)
        set(value) = preferences.edit().putInt(CURRENT_FORMATION,value).apply()

}