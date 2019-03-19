package com.calestu.squadscbfa.data.usecase

import com.calestu.squadscbfa.data.executor.BaseSchedulerProvider
import com.calestu.squadscbfa.data.mapper.mergeResults
import com.calestu.squadscbfa.data.mapper.toEntity
import com.calestu.squadscbfa.data.model.AppInfoModel
import com.calestu.squadscbfa.data.repository.AppInfoRepository
import com.calestu.squadscbfa.data.repository.ClubRepository
import com.calestu.squadscbfa.data.repository.CoachRepository
import com.calestu.squadscbfa.data.repository.PlayerRepository
import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.SingleTransformer
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function4
import javax.inject.Inject
import javax.inject.Singleton

class SyncUseCase @Inject constructor(
    private val appInfoRepository: AppInfoRepository,
    private val clubRepository: ClubRepository,
    private val playerRepository: PlayerRepository,
    private val coachRepository: CoachRepository,
    private val schedulerProvider: BaseSchedulerProvider
) {

    fun syncApp(): Single<Boolean> {
        return syncAppInfo()
            .subscribeOn(schedulerProvider.diskIO())
            .compose(syncAllData())
    }

    private fun syncAllData() = object : SingleTransformer<AppInfoModel, Boolean> {
        override fun apply(upstream: Single<AppInfoModel>): SingleSource<Boolean> {
            return upstream.flatMap {
                syncAllData(it)
            }
        }
    }

    private fun syncAppInfo() : Single<AppInfoModel> {
        return Single.zip(
            appInfoRepository.getRemoteAppInfo(),
            appInfoRepository.getLocalAppInfoToEmpty(),
            BiFunction { remote, local ->
                mergeResults(remote, local)
            }
        )
    }

    private fun syncAllData(appInfoModel: AppInfoModel): Single<Boolean> {
        return Single.zip(
            syncClubs(appInfoModel),
            syncPlayers(appInfoModel),
            syncCoaches(appInfoModel),
            saveAppInfo(appInfoModel),
            Function4{ clubs, players, coaches, appinfo ->
                true
            }
        )
    }

    private fun syncPlayers(appInfoModel: AppInfoModel): Single<Boolean> {
        return if (appInfoModel.syncPlayers) {
            playerRepository.fetchFromRemote()
        } else {
            Single.just(true)
        }
    }

    private fun syncCoaches(appInfoModel: AppInfoModel): Single<Boolean> {
        return if (appInfoModel.syncCoaches) {
            coachRepository.fetchFromRemote().map { true }
        } else {
            Single.just(true)
        }
    }

    private fun syncClubs(appInfoModel: AppInfoModel): Single<Boolean> {
        return if (appInfoModel.syncClubs) {
            clubRepository.insertLocalClubs().toSingleDefault(true)
        } else {
            Single.just(true)
        }
    }

    private fun saveAppInfo(appInfoModel: AppInfoModel): Single<Boolean> {
        return if (appInfoModel.firstOpen) {
            appInfoRepository.insertAppInfo(appInfoModel.toEntity()).toSingleDefault(true)
        } else {
            appInfoRepository.updateAppInfo(appInfoModel.toEntity()).toSingleDefault(true)
        }
    }

}