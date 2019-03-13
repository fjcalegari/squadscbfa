package com.calestu.squadscbfa.di.module

import com.calestu.squadscbfa.data.executor.BaseSchedulerProvider
import com.calestu.squadscbfa.data.executor.SchedulerProvider
import com.calestu.squadscbfa.data.repository.AppInfoRepository
import com.calestu.squadscbfa.data.repository.ClubRepository
import com.calestu.squadscbfa.data.repository.CoachRepository
import com.calestu.squadscbfa.data.repository.PlayerRepository
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

    @Singleton
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

}