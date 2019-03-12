package com.calestu.squadscbfa.data.repository

import com.calestu.squadscbfa.data.entity.AppInfoEntity
import com.calestu.squadscbfa.data.model.AppInfoModel
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

interface AppInfoRepository {

    fun getLocalAppInfo(): Single<AppInfoEntity>
    fun getLocalAppInfoToEmpty(): Single<AppInfoModel>
    fun getLocalCountAppInfo(): Single<Int>
    fun insertAppInfo(appInfoEntity: AppInfoEntity) : Completable
    fun updateAppInfo(appInfoEntity: AppInfoEntity) : Completable

    fun getRemoteAppInfo(): Single<AppInfoModel>

}