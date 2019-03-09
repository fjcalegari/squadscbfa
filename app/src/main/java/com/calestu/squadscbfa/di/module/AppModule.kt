package com.calestu.squadscbfa.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.calestu.squadscbfa.data.local.prefs.AppPreferences
import com.calestu.squadscbfa.data.local.prefs.PreferenceHelper
import com.calestu.squadscbfa.di.builder.ViewModelBuilder
import com.calestu.squadscbfa.di.qualifier.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelBuilder::class])
class AppModule {

    @Provides
    @Singleton
    @ApplicationContext
    fun provideApplicationContext(application: Application):Context = application

    @Provides
    fun providePreferenceHelper(@ApplicationContext context: Context): SharedPreferences {
        return PreferenceHelper.defaultPrefs(context)
    }

    @Provides
    fun provideAppPreferences(@ApplicationContext context: Context, preferenceHelper: SharedPreferences): AppPreferences {
        return AppPreferences(context, preferenceHelper)
    }

}