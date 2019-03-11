package com.calestu.squadscbfa.data.repository

import com.calestu.squadscbfa.data.entity.ClubEntity
import com.calestu.squadscbfa.data.mapper.toEntities
import com.calestu.squadscbfa.data.model.ClubModel
import com.calestu.squadscbfa.data.source.local.LocalSource
import com.calestu.squadscbfa.data.source.remote.RemoteSource
import io.reactivex.Completable
import io.reactivex.Flowable
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ClubRepositoryImpl @Inject constructor(
    private val localSource: LocalSource,
    private val remoteSource: RemoteSource
) : ClubRepository {


    override fun insertLocalClubs(): Completable {
        Timber.d("insertLocalClubs: ")
        return localSource.insertClubs(ClubModel().toEntities())
    }

    override fun getClubs(): Flowable<List<ClubEntity>> {
        return localSource.getClubs()
    }

}