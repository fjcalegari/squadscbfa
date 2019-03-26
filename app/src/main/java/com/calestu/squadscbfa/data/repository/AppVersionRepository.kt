package com.calestu.squadscbfa.data.repository

import com.calestu.squadscbfa.data.model.AppVersionResultModel
import io.reactivex.Completable
import io.reactivex.Single

interface AppVersionRepository {

    fun loadAppVersion(): Single<AppVersionResultModel>
    fun saveAppVersion(appVersionResultModel: AppVersionResultModel): Completable

}