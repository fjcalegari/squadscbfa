package com.calestu.squadscbfa.data.source.remote

import com.calestu.squadscbfa.data.entity.ClubEntity
import com.calestu.squadscbfa.data.model.AppInfoModel
import com.calestu.squadscbfa.data.model.CoachModel
import com.calestu.squadscbfa.data.model.PlayerModel
import com.google.firebase.database.DataSnapshot
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single

interface RemoteSource {

    // APP INFO
    fun getAppInfo(): Single<AppInfoModel>

    // CLUB
    fun getClubs(): Flowable<List<ClubEntity>>

    // PLAYER
    fun getPlayers(): Single<List<PlayerModel>>

    // PLAYER
    fun getCoaches(): Single<List<CoachModel>>

}