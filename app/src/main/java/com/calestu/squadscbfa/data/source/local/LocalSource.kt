package com.calestu.squadscbfa.data.source.local

import androidx.lifecycle.LiveData
import com.calestu.squadscbfa.data.entity.*
import com.calestu.squadscbfa.ui.module.squad.add.model.SquadAddModel
import io.reactivex.Completable
import io.reactivex.Flowable
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

    // CURRENT SQUAD
    fun getCurrentSquad(): LiveData<CurrentSquadEntity>
    fun getLocalCurrentSquad(): Single<CurrentSquadEntity>
    fun getCountCurrentSquad(): Single<Int>
    fun insertCurrentSquad(currentSquadEntity: CurrentSquadEntity) : Completable
    fun updateCurrentSquad(currentSquadEntity: CurrentSquadEntity) : Completable

}