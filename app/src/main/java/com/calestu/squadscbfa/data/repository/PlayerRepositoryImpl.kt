package com.calestu.squadscbfa.data.repository

import com.calestu.squadscbfa.data.entity.PlayerEntity
import com.calestu.squadscbfa.data.mapper.toEntity
import com.calestu.squadscbfa.data.model.PlayerModel
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
class PlayerRepositoryImpl @Inject constructor(
    private val localSource: LocalSource,
    private val remoteSource: RemoteSource
) : PlayerRepository {

    override fun fetchFromRemote(): Single<Boolean> {
        return remoteSource.getPlayers()
            .compose(mapperResultFromRemote())
            .compose(saveLocal())
    }

//    val transformer = object : SingleTransformer<List<PlayerEntity>, Boolean> {
//        override fun apply(upstream: Single<List<PlayerEntity>>): SingleSource<Boolean>{
//            return upstream.flatMap { localSource.insertPlayers(it).toSingleDefault(true)  }
//        }
//    }

    private fun saveLocal() = object : SingleTransformer<List<PlayerEntity>, Boolean> {
        override fun apply(upstream: Single<List<PlayerEntity>>): SingleSource<Boolean>{
            return upstream.flatMap {
                Timber.d("insertPlayers.Thread.name: ${Thread.currentThread().name}")
                localSource.insertPlayers(it).toSingleDefault(true)
            }
        }
    }

    private fun mapperResultFromRemote(): SingleTransformer<List<PlayerModel>, List<PlayerEntity>> {
        return SingleTransformer {it.map { list ->
            Timber.d("mapperResultFromRemote.Thread.name: ${Thread.currentThread().name}")
            list.mapNotNull { playerModel ->  playerModel.toEntity()} }
        }
    }

    override fun insertLocalPlayers(players: List<PlayerEntity>): Completable {
        return localSource.insertPlayers(players)
    }

}