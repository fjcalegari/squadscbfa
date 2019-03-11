package com.calestu.squadscbfa.data.usecase

import com.calestu.squadscbfa.data.executor.BaseSchedulerProvider
import com.calestu.squadscbfa.data.mapper.mergeResults
import com.calestu.squadscbfa.data.mapper.toEntity
import com.calestu.squadscbfa.data.model.AppInfoModel
import com.calestu.squadscbfa.data.repository.AppInfoRepository
import com.calestu.squadscbfa.data.repository.ClubRepository
import com.calestu.squadscbfa.util.ext.completableForUI
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.SingleTransformer
import io.reactivex.functions.BiFunction
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SyncUseCase @Inject constructor(
    private val appInfoRepository: AppInfoRepository,
    private val clubRepository: ClubRepository,
    private val schedulerProvider: BaseSchedulerProvider
) {

    fun syncApp(): Completable {
        return syncAppInfo()
//            .compose(getLocalCountAppInfo())
//            .compose(fetchUpdatePlayers())
//            .compose(fetchUpdateCoaches())
//            .compose(saveAppInfo())
            .doOnError {
                Timber.e(it, "syncApp: ${it.printStackTrace()}")
            }
            .flatMap {
                syncAllData(it)
            }
            .flatMapCompletable {
                Completable.complete()
            }
            .compose(completableForUI(schedulerProvider))
    }

    private fun syncAppInfo() : Single<AppInfoModel> {
        Timber.d("syncAppInfo: ")
        return Single.zip(
            appInfoRepository.getRemoteAppInfo(),
            appInfoRepository.getLocalAppInfoToEmpty(),
            BiFunction { remote, local ->
                mergeResults(remote, local)
            }
        )
    }

    private fun syncAllData(appInfoModel: AppInfoModel): Single<Boolean> {
        Timber.d("syncAllData: ")
        return Single.zip(
            syncClubs(appInfoModel),
            syncAppInfo(appInfoModel),
            BiFunction { clubs, appinfo ->
                true
            }
        )
    }

//    private fun syncAllData(appInfoModel: AppInfoModel) : Single<Boolean> {
//        Timber.d("syncAllData: ")
//        return Single.zip(
//            clubRepository.insertLocalClubs().toSingleDefault(true),
//            appInfoRepository.insertFirstAppInfo(appInfoModel).toSingleDefault(true),
//            BiFunction<Boolean, Boolean, Boolean>
//            { _, _ ->
//                true})
//    }

    private fun syncClubs(appInfoModel: AppInfoModel): Single<Boolean> {
        Timber.d("syncClubs: ")
        return if (appInfoModel.syncClubs) {
            clubRepository.insertLocalClubs().toSingleDefault(true)
        } else {
            Single.just(true)
        }
    }

    private fun syncAppInfo(appInfoModel: AppInfoModel): Single<Boolean> {
        Timber.d("syncAppInfo: ")
        return appInfoRepository.insertAppInfo(appInfoModel.toEntity()).toSingleDefault(true)
    }

}