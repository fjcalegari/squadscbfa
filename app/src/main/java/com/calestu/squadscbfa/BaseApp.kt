package com.calestu.squadscbfa

import android.app.Activity
import android.app.Application
import com.calestu.squadscbfa.di.builder.AppInjector
import com.calestu.squadscbfa.di.component.AppComponent
import com.calestu.squadscbfa.libs.log.LogImplementation
import com.facebook.stetho.Stetho
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class BaseApp: Application(), HasActivityInjector {

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityAndroidInjector
    }

    private lateinit var appComponent: AppComponent

    @Inject
    lateinit var activityAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        LogImplementation.init()

        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(applicationContext)
        }

        appComponent = AppInjector.init(this)

    }

}