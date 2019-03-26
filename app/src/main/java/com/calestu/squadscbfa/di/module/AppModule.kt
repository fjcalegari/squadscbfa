package com.calestu.squadscbfa.di.module

import android.app.Application
import android.content.Context
import com.calestu.squadscbfa.data.executor.BaseSchedulerProvider
import com.calestu.squadscbfa.data.executor.SchedulerProvider
import com.calestu.squadscbfa.data.source.AppDataManager
import com.calestu.squadscbfa.data.source.DataManager
import com.calestu.squadscbfa.data.source.prefs.PreferencesHelper
import com.calestu.squadscbfa.di.builder.viewmodel.ViewModelBuilder
import com.calestu.squadscbfa.di.qualifier.ApplicationContext
import com.calestu.squadscbfa.util.AppExecutors
import com.google.firebase.auth.FirebaseAuth
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
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun providePreferencesHelper(
        @ApplicationContext context: Context
    ): PreferencesHelper {
        return PreferencesHelper(context)
    }

    @Provides
    @Singleton
    fun provideAppDataManager(
        @ApplicationContext context: Context,
        preferencesHelper: PreferencesHelper,
        firebaseAuth: FirebaseAuth
    ): DataManager {
        return AppDataManager(context, preferencesHelper, firebaseAuth)
    }

    @Singleton
    @Provides
    fun provideSchedulerProvider(appExecutors: AppExecutors): BaseSchedulerProvider {
        return SchedulerProvider(appExecutors)
    }

}