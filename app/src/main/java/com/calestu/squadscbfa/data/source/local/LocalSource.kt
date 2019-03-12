package com.calestu.squadscbfa.data.source.local

import com.calestu.squadscbfa.data.entity.AppInfoEntity
import com.calestu.squadscbfa.data.entity.ClubEntity
import com.calestu.squadscbfa.data.entity.CoachEntity
import com.calestu.squadscbfa.data.entity.PlayerEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single

interface LocalSource {

    // APP INFO
    fun getAppInfo(): Single<AppInfoEntity>
    fun getCountAppInfo(): Single<Int>
    fun insertAppInfo(appInfoEntity: AppInfoEntity) : Completable
    fun updateAppInfo(appInfoEntity: AppInfoEntity) : Completable

    // CLUB
    fun getClubs(): Flowable<List<ClubEntity>>
    fun insertClubs(clubs : List<ClubEntity>) : Completable

    //PLAYER
    fun insertPlayers(players : List<PlayerEntity>) : Completable

    //COACH
    fun insertCoaches(coaches : List<CoachEntity>) : Completable

}