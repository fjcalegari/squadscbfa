package com.calestu.squadscbfa.data.repository

import com.calestu.squadscbfa.data.entity.ClubEntity
import com.calestu.squadscbfa.data.mapper.toEntity
import com.calestu.squadscbfa.data.model.type.ClubType
import com.calestu.squadscbfa.data.source.local.LocalSource
import com.calestu.squadscbfa.data.source.remote.RemoteSource
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ClubRepositoryImpl @Inject constructor(
    private val localSource: LocalSource,
    private val remoteSource: RemoteSource
) : ClubRepository {


    override fun insertLocalClubs(): Completable {
        return localSource.insertClubs(ClubType.values().map { it.toEntity() })
    }

    override fun getClubs(): Flowable<List<ClubEntity>> {
        return localSource.getClubs()
    }

}