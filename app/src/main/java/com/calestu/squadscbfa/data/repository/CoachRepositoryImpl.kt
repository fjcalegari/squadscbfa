package com.calestu.squadscbfa.data.repository

import com.calestu.squadscbfa.data.entity.CoachEntity
import com.calestu.squadscbfa.data.mapper.toEntity
import com.calestu.squadscbfa.data.source.local.LocalSource
import com.calestu.squadscbfa.data.source.remote.RemoteSource
import com.calestu.squadscbfa.data.source.remote.model.CoachRemoteModel
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class CoachRepositoryImpl @Inject constructor(
    private val localSource: LocalSource,
    private val remoteSource: RemoteSource
) : CoachRepository {

    override fun loadCoaches(): Completable {
        return getRemote().flatMapIterable { it }
            .flatMapCompletable {
                saveCoach(it.toEntity())
            }
    }

    override fun saveCoach(coachEntity: CoachEntity): Completable =
        localSource.saveCoach(coachEntity)

    private fun getRemote(): Flowable<List<CoachRemoteModel>> =
        remoteSource.getCoaches()

}