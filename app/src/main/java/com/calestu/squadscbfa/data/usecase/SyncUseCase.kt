package com.calestu.squadscbfa.data.usecase

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.calestu.squadscbfa.data.executor.BaseSchedulerProvider
import com.calestu.squadscbfa.data.model.AppVersionResultModel
import com.calestu.squadscbfa.data.repository.AppVersionRepository
import com.calestu.squadscbfa.data.repository.CoachRepository
import com.calestu.squadscbfa.data.repository.PlayerRepository
import com.calestu.squadscbfa.data.source.DataManager
import com.calestu.squadscbfa.ui.base.Resource
import com.calestu.squadscbfa.util.ext.toLiveData
import com.calestu.squadscbfa.util.ext.toState
import com.google.firebase.auth.FirebaseUser
import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.SingleTransformer
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class SyncUseCase @Inject constructor(
    private val dataManager: DataManager,
    private val appVersionRepository: AppVersionRepository,
    private val playerRepository: PlayerRepository,
    private val coachRepository: CoachRepository,
    private val schedulerProvider: BaseSchedulerProvider
) {

    private val result = MediatorLiveData<Resource<Boolean>>()

    private fun asLiveData() = result as LiveData<Resource<Boolean>>

    fun sync(): LiveData<Resource<Boolean>> {
        setValue(Resource.loading(null))

        result.addSource(dataManager.getCurrentUser()
            .compose(loadAppVersion())
            .compose(loadData())
            .compose(saveDataManager())
            .compose(saveAppVersion())
            .subscribeOn(schedulerProvider.networkIO())
            .observeOn(schedulerProvider.ui())
            .toFlowable()
            .toState()
            .toLiveData()) { newData ->
               setValue(newData)
            }

//        result.addSource(Single.just(true)
//            .subscribeOn(schedulerProvider.io())
//            .observeOn(schedulerProvider.ui())
//            .toFlowable()
//            .toState()
//            .toLiveData()) { newData ->
//                setValue(newData)
//            }

        return asLiveData()
    }

    private fun loadAppVersion() = object : SingleTransformer<FirebaseUser, AppVersionResultModel> {
        override fun apply(upstream: Single<FirebaseUser>): SingleSource<AppVersionResultModel> {
            return upstream.flatMap {
                appVersionRepository.loadAppVersion()
            }
        }
    }

    private fun loadData() = object : SingleTransformer<AppVersionResultModel, AppVersionResultModel> {
        override fun apply(upstream: Single<AppVersionResultModel>): SingleSource<AppVersionResultModel> {
            return upstream.flatMap { version ->
                loadAllData(version)
            }
        }
    }

    private fun loadAllData(appVersionResultModel: AppVersionResultModel): Single<AppVersionResultModel> {
        return Single.zip(
            syncCoaches(appVersionResultModel),
            syncPlayers(appVersionResultModel),
            BiFunction{ coaches, players ->
                appVersionResultModel
            }
        )
    }

    private fun syncCoaches(appVersionResultModel: AppVersionResultModel): Single<Boolean> {
        return if (appVersionResultModel.syncCoach) {
            coachRepository.loadCoaches().toSingleDefault(true)
        } else {
            Single.just(true)
        }
    }

    private fun syncPlayers(appVersionResultModel: AppVersionResultModel): Single<Boolean> {
        return playerRepository.loadPlayers(appVersionResultModel).toSingleDefault(true)
    }

    private fun saveAppVersion() = object : SingleTransformer<AppVersionResultModel, Boolean> {
        override fun apply(upstream: Single<AppVersionResultModel>): SingleSource<Boolean> {
            return upstream.flatMap { version ->
                appVersionRepository.saveAppVersion(version)
                    .toSingleDefault(true)
            }
        }
    }

    private fun saveDataManager() = object : SingleTransformer<AppVersionResultModel, AppVersionResultModel> {
        override fun apply(upstream: Single<AppVersionResultModel>): SingleSource<AppVersionResultModel> {
            return upstream.flatMap { version ->
                Single.fromCallable {
                    dataManager.saveCurrentRound(version.appVersionRemoteModel.round)
                }.map { return@map version }
            }
        }
    }

    @MainThread
    private fun setValue(newValue: Resource<Boolean>) {
        if (result.value != newValue) {
            result.value = newValue
        }
    }

}