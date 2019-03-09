package com.calestu.squadscbfa.libs.log

import com.calestu.squadscbfa.BuildConfig
import timber.log.Timber

class LogImplementation {

    companion object {
        @JvmStatic
        fun init() {
            if (BuildConfig.DEBUG) {
                Timber.plant(DebugTree())
            }
        }
    }

}