package com.calestu.squadscbfa.di.module

import com.calestu.squadscbfa.data.executor.BaseSchedulerProvider
import com.calestu.squadscbfa.data.repository.AppVersionRepository
import com.calestu.squadscbfa.data.repository.CoachRepository
import com.calestu.squadscbfa.data.repository.PlayerRepository
import com.calestu.squadscbfa.data.repository.SquadRepository
import com.calestu.squadscbfa.data.source.DataManager
import com.calestu.squadscbfa.data.usecase.MySquadUseCase
import com.calestu.squadscbfa.data.usecase.SquadEditUseCase
import com.calestu.squadscbfa.data.usecase.PlayerUseCase
import com.calestu.squadscbfa.data.usecase.SyncUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun provideSyncUseCase(
        appDataManager: DataManager,
        appVersionRepository: AppVersionRepository,
        playerRepository: PlayerRepository,
        coachRepository: CoachRepository,
        schedulerProvider: BaseSchedulerProvider): SyncUseCase {
        return SyncUseCase(
            appDataManager,
            appVersionRepository,
            playerRepository,
            coachRepository,
            schedulerProvider
        )
    }

    @Provides
    fun provideMySquadUseCase(
        appDataManager: DataManager,
        squadRepository: SquadRepository,
        playerRepository: PlayerRepository,
        coachRepository: CoachRepository,
        schedulerProvider: BaseSchedulerProvider): MySquadUseCase {
        return MySquadUseCase(
            appDataManager,
            squadRepository,
            playerRepository,
            coachRepository,
            schedulerProvider
        )
    }

    @Provides
    fun provideSquadEditUseCase(
        appDataManager: DataManager,
        squadRepository: SquadRepository,
        playerRepository: PlayerRepository,
        coachRepository: CoachRepository,
        schedulerProvider: BaseSchedulerProvider): SquadEditUseCase {
        return SquadEditUseCase(
            appDataManager,
            squadRepository,
            playerRepository,
            coachRepository,
            schedulerProvider
        )
    }

    @Provides
    fun providePlayerUseCase(
        appDataManager: DataManager,
        squadRepository: SquadRepository,
        playerRepository: PlayerRepository,
        schedulerProvider: BaseSchedulerProvider): PlayerUseCase {
        return PlayerUseCase(
            appDataManager,
            squadRepository,
            playerRepository,
            schedulerProvider
        )
    }

}