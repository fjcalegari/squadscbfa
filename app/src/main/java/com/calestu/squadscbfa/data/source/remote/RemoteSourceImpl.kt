package com.calestu.squadscbfa.data.source.remote

import com.calestu.squadscbfa.data.entity.ClubEntity
import com.calestu.squadscbfa.data.mapper.toAppInfoModel
import com.calestu.squadscbfa.data.model.AppInfoModel
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteSourceImpl @Inject constructor(
): FirebaseRemoteSource(), RemoteSource {

    override fun getAppInfo(): Single<AppInfoModel> {
        return versionChild().observeSingleValueEvent().map { it.toAppInfoModel() }
    }

    override fun getClubs(): Flowable<List<ClubEntity>> {
        return Flowable.just(listOf())
    }

}