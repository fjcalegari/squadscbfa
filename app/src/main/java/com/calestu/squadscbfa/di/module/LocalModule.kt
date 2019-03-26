package com.calestu.squadscbfa.di.module

import android.content.Context
import com.calestu.squadscbfa.data.source.local.*
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

    @Singleton
    @Provides
    fun provideSquadSource(localDatabase: LocalDatabase): SquadSource {
        return SquadSourceImpl(localDatabase)
    }

}