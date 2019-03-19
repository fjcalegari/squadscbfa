package com.calestu.squadscbfa.data.repository

import androidx.lifecycle.LiveData
import com.calestu.squadscbfa.data.entity.CurrentSquadEntity
import com.calestu.squadscbfa.ui.module.squad.add.model.SquadAddModel
import io.reactivex.Completable
import io.reactivex.Single

interface CurrentSquadRepository {

    fun getCurrentSquad(): LiveData<CurrentSquadEntity>
    fun getLocalCurrentSquad(): Single<CurrentSquadEntity>
    fun getLocalCountCurrentSquad(): Single<Int>
    fun insertCurrentSquad(currentSquadEntity: CurrentSquadEntity) : Completable
    fun updateCurrentSquad(currentSquadEntity: CurrentSquadEntity) : Completable

}