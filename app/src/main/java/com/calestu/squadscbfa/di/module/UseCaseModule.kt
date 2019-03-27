package com.calestu.squadscbfa.di.module

import com.calestu.squadscbfa.data.executor.BaseSchedulerProvider
import com.calestu.squadscbfa.data.repository.*
import com.calestu.squadscbfa.data.source.DataManager
import com.calestu.squadscbfa.data.usecase.*
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
        currentSquadRepository: CurrentSquadRepository,
        schedulerProvider: BaseSchedulerProvider): MySquadUseCase {
        return MySquadUseCase(
            appDataManager,
            squadRepository,
            playerRepository,
            coachRepository,
            currentSquadRepository,
            schedulerProvider
        )
    }

    @Provides
    fun provideSquadEditUseCase(
        appDataManager: DataManager,
        squadRepository: SquadRepository,
        playerRepository: PlayerRepository,
        coachRepository: CoachRepository,
        currentSquadRepository: CurrentSquadRepository,
        schedulerProvider: BaseSchedulerProvider): SquadEditUseCase {
        return SquadEditUseCase(
            appDataManager,
            squadRepository,
            playerRepository,
            coachRepository,
            currentSquadRepository,
            schedulerProvider
        )
    }

    @Provides
    fun provideSquadAddUseCase(
        appDataManager: DataManager,
        playerRepository: PlayerRepository,
        coachRepository: CoachRepository,
        currentSquadRepository: CurrentSquadRepository,
        schedulerProvider: BaseSchedulerProvider): SquadAddUseCase {
        return SquadAddUseCase(
            appDataManager,
            playerRepository,
            coachRepository,
            currentSquadRepository,
            schedulerProvider
        )
    }

    @Provides
    fun provideSquadPlayerUseCase(
        appDataManager: DataManager,
        playerRepository: PlayerRepository,
        squadRepository: SquadRepository,
        coachRepository: CoachRepository,
        currentSquadRepository: CurrentSquadRepository,
        schedulerProvider: BaseSchedulerProvider): SquadPlayerUseCase {
        return SquadPlayerUseCase(
            appDataManager,
            playerRepository,
            squadRepository,
            coachRepository,
            currentSquadRepository,
            schedulerProvider
        )
    }

}