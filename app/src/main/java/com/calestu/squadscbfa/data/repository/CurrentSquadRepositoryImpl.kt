package com.calestu.squadscbfa.data.repository

import androidx.lifecycle.LiveData
import com.calestu.squadscbfa.data.entity.CurrentSquadEntity
import com.calestu.squadscbfa.data.source.local.LocalSource
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class CurrentSquadRepositoryImpl @Inject constructor(
    private val localSource: LocalSource
) : CurrentSquadRepository {

    override fun getCurrentSquad(): LiveData<CurrentSquadEntity> {
        return localSource.getCurrentSquad()
    }

    override fun getLocalCurrentSquad(): Single<CurrentSquadEntity> {
        return localSource.getLocalCurrentSquad()
    }

//    private fun getCurrentSquad(): SingleTransformer<Int, CurrentSquadEntity> {
//        return SingleTransformer {
//            it.flatMap {count ->
//                if (count > 0) {
//                    localSource.getCurrentSquad()
//                } else {
//                    localSource.insertCurrentSquad(CurrentSquadEntity())
//                    localSource.getCurrentSquad()
//                }
//            }
//        }
//    }

    override fun getLocalCountCurrentSquad(): Single<Int> {
        return localSource.getCountCurrentSquad()
    }

    override fun insertCurrentSquad(currentSquadEntity: CurrentSquadEntity): Completable {
        return localSource.insertCurrentSquad(currentSquadEntity)
    }

    override fun updateCurrentSquad(currentSquadEntity: CurrentSquadEntity): Completable {
        return localSource.updateCurrentSquad(currentSquadEntity)
    }

}