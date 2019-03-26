package com.calestu.squadscbfa.data.repository

import com.calestu.squadscbfa.data.entity.AppVersionEntity
import com.calestu.squadscbfa.data.executor.BaseSchedulerProvider
import com.calestu.squadscbfa.data.mapper.toSaveEntity
import com.calestu.squadscbfa.data.model.AppVersionResultModel
import com.calestu.squadscbfa.data.source.local.LocalSource
import com.calestu.squadscbfa.data.source.remote.RemoteSource
import com.calestu.squadscbfa.data.source.remote.model.AppVersionRemoteModel
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.SingleTransformer
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class AppVersionRepositoryImpl @Inject constructor(
    private val localSource: LocalSource,
    private val remoteSource: RemoteSource,
    private val schedulerProvider: BaseSchedulerProvider
) : AppVersionRepository {

    override fun loadAppVersion(): Single<AppVersionResultModel> {
        return Single.zip(
            getLocal(),
            getRemote(),
            BiFunction { local, remote ->
                AppVersionResultModel(local, remote)
            }
        )
    }

    private fun getLocal(): Single<AppVersionEntity> =
        localSource.countAppVersion().compose(getSyncAppVersion()).subscribeOn(schedulerProvider.diskIO()).observeOn(schedulerProvider.diskIO())

    private fun getSyncAppVersion() = object : SingleTransformer<Int, AppVersionEntity> {
        override fun apply(upstream: Single<Int>): SingleSource<AppVersionEntity> {
            return upstream.flatMap {
                if (it == 0) {
                    val appVersionEntity = AppVersionEntity()
                    localSource.insertAppVersion(appVersionEntity).toSingleDefault(appVersionEntity)
                } else {
                    localSource.getAppVersion()
                }
            }
        }
    }

    override fun saveAppVersion(appVersionResultModel: AppVersionResultModel): Completable =
        localSource.updateAppVersion(appVersionResultModel.toSaveEntity())

    private fun getRemote(): Single<AppVersionRemoteModel> =
        remoteSource.getAppVersion()

}