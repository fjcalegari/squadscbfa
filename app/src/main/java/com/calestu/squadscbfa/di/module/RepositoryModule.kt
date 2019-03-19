package com.calestu.squadscbfa.di.module

import com.calestu.squadscbfa.data.repository.*
import com.calestu.squadscbfa.data.source.local.LocalSource
import com.calestu.squadscbfa.data.source.remote.RemoteSource
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideAppInfoRepository(localSource: LocalSource, remoteSource: RemoteSource): AppInfoRepository {
        return AppInfoRepositoryImpl(localSource,remoteSource)
    }

    @Provides
    fun provideClubRepository(localSource: LocalSource, remoteSource: RemoteSource): ClubRepository {
        return ClubRepositoryImpl(localSource,remoteSource)
    }

    @Provides
    fun providePlayerRepository(localSource: LocalSource, remoteSource: RemoteSource): PlayerRepository {
        return PlayerRepositoryImpl(localSource,remoteSource)
    }

    @Provides
    fun provideCoachRepository(localSource: LocalSource, remoteSource: RemoteSource): CoachRepository {
        return CoachRepositoryImpl(localSource,remoteSource)
    }

    @Provides
    fun provideCurrentSquadRepository(localSource: LocalSource): CurrentSquadRepository {
        return CurrentSquadRepositoryImpl(localSource)
    }

}