package com.calestu.squadscbfa.data.repository

import com.calestu.squadscbfa.data.entity.CurrentSquadEntity
import com.calestu.squadscbfa.data.source.local.LocalSource
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class CurrentSquadRepositoryImpl @Inject constructor(
    private val localSource: LocalSource
) : CurrentSquadRepository {

    override fun currentSquad(): Flowable<CurrentSquadEntity> {
        return localSource.currentSquad()
    }

    override fun getLocalCurrentSquad(): Single<CurrentSquadEntity> =
        localSource.getLocalCurrentSquad()

    override fun getLocalCountCurrentSquad(): Single<Int> =
        localSource.countCurrentSquad()

    override fun insertCurrentSquad(currentSquadEntity: CurrentSquadEntity): Completable =
        localSource.insertCurrentSquad(currentSquadEntity)

    override fun updateCurrentSquad(currentSquadEntity: CurrentSquadEntity): Completable =
        localSource.updateCurrentSquad(currentSquadEntity)

}