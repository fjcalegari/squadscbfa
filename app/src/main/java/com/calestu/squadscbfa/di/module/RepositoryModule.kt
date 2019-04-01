package com.calestu.squadscbfa.di.module

import com.calestu.squadscbfa.data.executor.BaseSchedulerProvider
import com.calestu.squadscbfa.data.repository.*
import com.calestu.squadscbfa.data.source.local.LocalSource
import com.calestu.squadscbfa.data.source.local.SquadSource
import com.calestu.squadscbfa.data.source.remote.RemoteSource
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideAppVersionRepository(
        localSource: LocalSource,
        remoteSource: RemoteSource,
        schedulerProvider: BaseSchedulerProvider
    ): AppVersionRepository {
        return AppVersionRepositoryImpl(localSource, remoteSource, schedulerProvider)
    }

    @Provides
    fun provideSquadRepository(
        localSource: LocalSource,
        squadSource: SquadSource
    ): SquadRepository {
        return SquadRepositoryImpl(localSource, squadSource)
    }

    @Provides
    fun providePlayerRepository(localSource: LocalSource, remoteSource: RemoteSource): PlayerRepository {
        return PlayerRepositoryImpl(localSource,remoteSource)
    }

    @Provides
    fun provideCoachRepository(localSource: LocalSource, remoteSource: RemoteSource): CoachRepository {
        return CoachRepositoryImpl(localSource,remoteSource)
    }

}