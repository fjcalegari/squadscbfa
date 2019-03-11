package com.calestu.squadscbfa.di.module

import com.calestu.squadscbfa.data.repository.AppInfoRepository
import com.calestu.squadscbfa.data.repository.AppInfoRepositoryImpl
import com.calestu.squadscbfa.data.repository.ClubRepository
import com.calestu.squadscbfa.data.repository.ClubRepositoryImpl
import com.calestu.squadscbfa.data.source.local.LocalSource
import com.calestu.squadscbfa.data.source.remote.RemoteSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideAppInfoRepository(localSource: LocalSource, remoteSource: RemoteSource): AppInfoRepository {
        return AppInfoRepositoryImpl(localSource,remoteSource)
    }

    @Singleton
    @Provides
    fun provideClubRepository(localSource: LocalSource, remoteSource: RemoteSource): ClubRepository {
        return ClubRepositoryImpl(localSource,remoteSource)
    }


}