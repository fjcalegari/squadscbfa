package com.calestu.squadscbfa.di.module

import android.content.Context
import com.calestu.squadscbfa.data.source.local.LocalDatabase
import com.calestu.squadscbfa.data.source.local.LocalSource
import com.calestu.squadscbfa.data.source.local.LocalSourceImpl
import com.calestu.squadscbfa.di.qualifier.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): LocalDatabase {
        return LocalDatabase.getInstance(context)
    }

    @Singleton
    @Provides
    fun provideLocalSource(localDatabase: LocalDatabase): LocalSource {
        return LocalSourceImpl(localDatabase)
    }

}