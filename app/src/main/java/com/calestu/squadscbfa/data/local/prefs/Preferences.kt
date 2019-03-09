package com.calestu.squadscbfa.data.local.prefs

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

open class Preferences(prefs: SharedPreferences): SharedPreferences by prefs {
    val TAG = "Preferences"
    val editor: SharedPreferences.Editor by lazy { edit() }

    constructor(context: Context) : this(prefs = PreferenceManager.getDefaultSharedPreferences(context)) {
    }

    public inner class Preference<T>: ReadWriteProperty<SharedPreferences, T> {
        //val type: T? = null
        @Suppress("UNCHECKED_CAST", "IMPLICIT_CAST_TO_UNIT_OR_ANY")
        override fun getValue(thisRef: SharedPreferences, property: KProperty<*>): T {
            return when (property.returnType.toString()) {
                "kotlin.Int?" -> thisRef.getInt(property.name, 0)
                "kotlin.Boolean?" -> thisRef.getBoolean(property.name, false)
                "kotlin.String?" -> thisRef.getString(property.name, null)
                "kotlin.Float?" -> thisRef.getFloat(property.name, 0f)
                "kotlin.Long?" -> thisRef.getLong(property.name, 0L)
                else -> null
            } as T

            /*
            return when (type) {
                is Int -> {
                    Log.d(TAG, "is Int");
                    thisRef.getInt(property.name, 0)
                }
                is Boolean -> thisRef.getBoolean(property.name, false)
                is String -> {
                    Log.d(TAG, "is String");
                    thisRef.getString(property.name, null)
                }
                is Float -> thisRef.getFloat(property.name, 0f)
                is Long -> thisRef.getLong(property.name, 0L)
                else -> {
                    Log.d(TAG, "is ?");
                    null
                }
            } as T
            return when (property.returnType) {
                kotlin.Boolean::class.java    -> thisRef.getBoolean(property.name, false)
                kotlin.Float::class.java      -> thisRef.getFloat(property.name, 0f)
                kotlin.Int::class.java        -> thisRef.getInt(property.name, 0)
                kotlin.Long::class.java       -> thisRef.getLong(property.name, 0L)
                kotlin.String::class.java     -> thisRef.getString(property.name, null)
                java.lang.Boolean::class.java -> thisRef.getBoolean(property.name, false)
                java.lang.Float::class.java   -> thisRef.getFloat(property.name, 0f)
                java.lang.Integer::class.java -> thisRef.getInt(property.name, 0)
                java.lang.Long::class.java    -> thisRef.getLong(property.name, 0L)
                java.lang.String::class.java  -> thisRef.getString(property.name, null)
            } as T
            */
        }

        override fun setValue(thisRef: SharedPreferences, property: KProperty<*>, value: T) {
            when (value) {
                is Int -> editor.putInt(property.name, value)
                is Boolean -> editor.putBoolean(property.name, value)
                is String -> editor.putString(property.name, value)
                is Float -> editor.putFloat(property.name, value)
                is Long -> editor.putLong(property.name, value)
                else -> null!!
            }
        }
    }

    public inner class IntPreference: ReadWriteProperty<SharedPreferences, Int> {
        override fun getValue(thisRef: SharedPreferences, property: KProperty<*>): Int {
            return thisRef.getInt(property.name, 0)
        }

        override fun setValue(thisRef: SharedPreferences, property: KProperty<*>, value: Int) {
            editor.putInt(property.name, value)
        }
    }

    public inner class StringPreference: ReadWriteProperty<SharedPreferences, String> {
        override fun getValue(thisRef: SharedPreferences, property: KProperty<*>): String {
            return thisRef.getString(property.name, null)
        }

        override fun setValue(thisRef: SharedPreferences, property: KProperty<*>, value: String) {
            editor.putString(property.name, value)
        }
    }

    /*
    TODO
    fun edit(func: T?.() -> Unit) {
        func()
        editor.apply()
    }
    */
}