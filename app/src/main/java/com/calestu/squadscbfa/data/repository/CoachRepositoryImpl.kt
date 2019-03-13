package com.calestu.squadscbfa.data.repository

import com.calestu.squadscbfa.data.entity.CoachEntity
import com.calestu.squadscbfa.data.model.CoachModel
import com.calestu.squadscbfa.data.source.local.LocalSource
import com.calestu.squadscbfa.data.source.remote.RemoteSource
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoachRepositoryImpl @Inject constructor(
    private val localSource: LocalSource,
    private val remoteSource: RemoteSource
) : CoachRepository {

    override fun getRemoteCoaches(): Single<List<CoachModel>> {
        return remoteSource.getCoaches()
    }

    override fun insertLocalCoaches(coaches: List<CoachEntity>): Completable {
        return localSource.insertCoaches(coaches)
    }

}