package com.calestu.squadscbfa.di.module

import com.calestu.squadscbfa.data.executor.BaseSchedulerProvider
import com.calestu.squadscbfa.data.executor.SchedulerProvider
import com.calestu.squadscbfa.data.repository.*
import com.calestu.squadscbfa.data.usecase.SquadAddUseCase
import com.calestu.squadscbfa.data.usecase.SyncUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {

    @Singleton
    @Provides
    fun provideSchedulerProvider(): BaseSchedulerProvider {
        return SchedulerProvider()
    }

    @Provides
    fun provideSyncUseCase(appInfoRepository: AppInfoRepository,
                           clubRepository: ClubRepository,
                           playerRepository: PlayerRepository,
                           coachRepository: CoachRepository,
                           schedulerProvider: BaseSchedulerProvider): SyncUseCase {
        return SyncUseCase(
            appInfoRepository,
            clubRepository,
            playerRepository,
            coachRepository,
            schedulerProvider
        )
    }

    @Singleton
    @Provides
    fun provideSquadAddUseCase(
                           clubRepository: ClubRepository,
                           playerRepository: PlayerRepository,
                           coachRepository: CoachRepository,
                           currentSquadRepository: CurrentSquadRepository,
                           schedulerProvider: BaseSchedulerProvider): SquadAddUseCase {
        return SquadAddUseCase(
            clubRepository,
            playerRepository,
            coachRepository,
            currentSquadRepository,
            schedulerProvider
        )
    }

}