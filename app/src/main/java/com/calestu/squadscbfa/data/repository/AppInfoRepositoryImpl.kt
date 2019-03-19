package com.calestu.squadscbfa.data.repository

import com.calestu.squadscbfa.data.entity.AppInfoEntity
import com.calestu.squadscbfa.data.mapper.fromEntity
import com.calestu.squadscbfa.data.model.AppInfoModel
import com.calestu.squadscbfa.data.source.local.LocalSource
import com.calestu.squadscbfa.data.source.remote.RemoteSource
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.SingleTransformer
import javax.inject.Inject
import javax.inject.Singleton

class AppInfoRepositoryImpl @Inject constructor(
    private val localSource: LocalSource,
    private val remoteSource: RemoteSource
) : AppInfoRepository {

    override fun getLocalAppInfoToEmpty(): Single<AppInfoModel> {
        return getLocalCountAppInfo()
            .compose(getAppInfoModel())
    }

    private fun getAppInfoModel(): SingleTransformer<Int, AppInfoModel> {
        return SingleTransformer {
            it.flatMap {count ->
                if (count > 0) {
                    getLocalAppInfo().map {ent -> ent.fromEntity() }
                } else {
                    Single.just(AppInfoModel(emptyResult = true))
                }
            }
        }
    }

    override fun insertAppInfo(appInfoEntity: AppInfoEntity): Completable {
        return localSource.insertAppInfo(appInfoEntity)
    }

    override fun updateAppInfo(appInfoEntity: AppInfoEntity): Completable {
        return localSource.updateAppInfo(appInfoEntity)
    }

    override fun getLocalCountAppInfo(): Single<Int> {
        return localSource.getCountAppInfo()
    }

    override fun getLocalAppInfo(): Single<AppInfoEntity> {
        return localSource.getAppInfo()
    }

    override fun getRemoteAppInfo(): Single<AppInfoModel> {
        return remoteSource.getAppInfo()
    }

}