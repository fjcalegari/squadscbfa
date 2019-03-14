package com.calestu.squadscbfa.data.repository

import com.calestu.squadscbfa.data.entity.CoachEntity
import com.calestu.squadscbfa.data.mapper.toEntity
import com.calestu.squadscbfa.data.model.CoachModel
import com.calestu.squadscbfa.data.source.local.LocalSource
import com.calestu.squadscbfa.data.source.remote.RemoteSource
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.SingleTransformer
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoachRepositoryImpl @Inject constructor(
    private val localSource: LocalSource,
    private val remoteSource: RemoteSource
) : CoachRepository {

    override fun fetchFromRemote(): Single<Boolean> {
        return remoteSource.getCoaches()
            .compose(mapperResultFromRemote())
            .compose(saveLocal())
    }

    private fun saveLocal() = object : SingleTransformer<List<CoachEntity>, Boolean> {
        override fun apply(upstream: Single<List<CoachEntity>>): SingleSource<Boolean> {
            return upstream.flatMap {
                Timber.d("insertCoaches.Thread.name: ${Thread.currentThread().name}")
                localSource.insertCoaches(it).toSingleDefault(true)
            }
        }
    }

    private fun mapperResultFromRemote(): SingleTransformer<List<CoachModel>, List<CoachEntity>> {
        return SingleTransformer {it.map { list ->
            Timber.d("mapperResultFromRemote.Thread.name: ${Thread.currentThread().name}")
            list.mapNotNull { coachModel ->  coachModel.toEntity()} }
        }
    }

    override fun insertLocalCoaches(coaches: List<CoachEntity>): Completable {
        return localSource.insertCoaches(coaches)
    }

}