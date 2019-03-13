package com.calestu.squadscbfa.data.usecase

import com.calestu.squadscbfa.data.executor.BaseSchedulerProvider
import com.calestu.squadscbfa.data.mapper.mergeResults
import com.calestu.squadscbfa.data.mapper.toEntity
import com.calestu.squadscbfa.data.model.AppInfoModel
import com.calestu.squadscbfa.data.repository.AppInfoRepository
import com.calestu.squadscbfa.data.repository.ClubRepository
import com.calestu.squadscbfa.data.repository.CoachRepository
import com.calestu.squadscbfa.data.repository.PlayerRepository
import com.calestu.squadscbfa.util.ext.singleForUI
import com.calestu.squadscbfa.util.ext.singleIO
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function4
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SyncUseCase @Inject constructor(
    private val appInfoRepository: AppInfoRepository,
    private val clubRepository: ClubRepository,
    private val playerRepository: PlayerRepository,
    private val coachRepository: CoachRepository,
    private val schedulerProvider: BaseSchedulerProvider
) {

    fun syncApp(): Single<Boolean> {
        Timber.d("syncApp: ")
        return playerRepository.fetchFromRemote()
            .compose(singleIO(schedulerProvider))
    }
//    fun syncApp(): Single<Boolean> {
//        return syncAppInfo()
//            .flatMap {
////                syncAllData(it).compose(singleIO(schedulerProvider))
//                syncAllData(it)
//            }
//            .compose(singleForUI(schedulerProvider))
//    }

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
        Timber.d("syncAllData: $appInfoModel")
        return syncPlayers(appInfoModel)
//        Single.zip(
//            syncClubs(appInfoModel),
//            syncPlayers(appInfoModel),
//            syncCoaches(appInfoModel),
//            syncAppInfo(appInfoModel),
//            Function4{ clubs, players, coaches, appinfo ->
//                true
//            }
//        )
    }

//    private fun syncAllData(appInfoModel: AppInfoModel): Single<Boolean> {
//        Timber.d("syncAllData: $appInfoModel")
//        return Single.zip(
//            syncClubs(appInfoModel),
//            syncPlayers(appInfoModel),
//            syncCoaches(appInfoModel),
//            syncAppInfo(appInfoModel),
//            Function4{ clubs, players, coaches, appinfo ->
//                true
//            }
//        )
//    }

    private fun syncPlayers(appInfoModel: AppInfoModel): Single<Boolean> {
        appInfoModel.syncPlayers = true
        return if (appInfoModel.syncPlayers) {
            playerRepository.fetchFromRemote()
        } else {
            Single.just(true)
        }
    }

    private fun syncCoaches(appInfoModel: AppInfoModel): Single<Boolean> {
        appInfoModel.syncCoaches = true
        return if (appInfoModel.syncCoaches) {
            coachRepository.getRemoteCoaches().map { true }
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

    private fun syncAppInfo(appInfoModel: AppInfoModel): Single<Boolean> {
        return if (appInfoModel.firstOpen) {
            appInfoRepository.insertAppInfo(appInfoModel.toEntity()).toSingleDefault(true)
        } else {
            appInfoRepository.updateAppInfo(appInfoModel.toEntity()).toSingleDefault(true)
        }
    }

}