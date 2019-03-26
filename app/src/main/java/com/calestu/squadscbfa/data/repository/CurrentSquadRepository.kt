package com.calestu.squadscbfa.data.repository

import com.calestu.squadscbfa.data.entity.CurrentSquadEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface CurrentSquadRepository {

    fun currentSquad(): Flowable<CurrentSquadEntity>
    fun getLocalCurrentSquad(): Single<CurrentSquadEntity>
    fun getLocalCountCurrentSquad(): Single<Int>
    fun insertCurrentSquad(currentSquadEntity: CurrentSquadEntity) : Completable
    fun updateCurrentSquad(currentSquadEntity: CurrentSquadEntity) : Completable

}