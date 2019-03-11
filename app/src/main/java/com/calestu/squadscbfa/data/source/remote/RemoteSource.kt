package com.calestu.squadscbfa.data.source.remote

import com.calestu.squadscbfa.data.entity.ClubEntity
import com.calestu.squadscbfa.data.model.AppInfoModel
import io.reactivex.Flowable
import io.reactivex.Single

interface RemoteSource {

    // APP INFO
    fun getAppInfo(): Single<AppInfoModel>

    // CLUB
    fun getClubs(): Flowable<List<ClubEntity>>

}