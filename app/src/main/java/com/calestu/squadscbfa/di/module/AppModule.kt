package com.calestu.squadscbfa.di.module

import android.app.Application
import android.content.Context
import com.calestu.squadscbfa.data.source.AppDataManager
import com.calestu.squadscbfa.data.source.DataManager
import com.calestu.squadscbfa.di.builder.viewmodel.ViewModelBuilder
import com.calestu.squadscbfa.di.qualifier.ApplicationContext
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
    @Singleton
    fun provideAppDataManager(@ApplicationContext context: Context, firebaseAuth: FirebaseAuth): DataManager {
        return AppDataManager(context, firebaseAuth)
    }

}