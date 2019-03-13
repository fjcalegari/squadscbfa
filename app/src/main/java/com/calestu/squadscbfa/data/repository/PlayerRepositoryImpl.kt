package com.calestu.squadscbfa.data.repository

import com.calestu.squadscbfa.data.entity.PlayerEntity
import com.calestu.squadscbfa.data.mapper.toEntity
import com.calestu.squadscbfa.data.model.PlayerModel
import com.calestu.squadscbfa.data.source.local.LocalSource
import com.calestu.squadscbfa.data.source.remote.RemoteSource
import io.reactivex.*
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlayerRepositoryImpl @Inject constructor(
    private val localSource: LocalSource,
    private val remoteSource: RemoteSource
) : PlayerRepository {

    override fun fetchFromRemote(): Single<Boolean> {
        Timber.d("fetchFromRemote: ")
        return remoteSource.getPlayers()
            .subscribeOn(Schedulers.io())
            .compose(mapperResultFromRemote())
            .compose(this.transformer)
//            .compose(saveLocal())
//            .flatMapCompletable { insertLocalPlayers(it) }
    }

    val transformer = object : SingleTransformer<List<PlayerEntity>, Boolean> {
        override fun apply(upstream: Single<List<PlayerEntity>>): SingleSource<Boolean>{
            Timber.d("transformer.apply: ")
            return upstream.flatMap { localSource.insertPlayers(it).toSingleDefault(true)  }
        }
    }

    fun saveLocal() = object : SingleTransformer<List<PlayerEntity>, Boolean> {
        override fun apply(upstream: Single<List<PlayerEntity>>): SingleSource<Boolean>{
            return upstream.flatMap { localSource.insertPlayers(it).toSingleDefault(true)  }
        }
    }


    private fun mapperResultFromRemote(): SingleTransformer<List<PlayerModel>, List<PlayerEntity>> {
        Timber.d("mapperResultFromRemote: ")
//        return SingleTransformer {}
        return SingleTransformer {it.map { list -> list.mapNotNull { playerModel ->  playerModel.toEntity()} }}
    }

    override fun insertLocalPlayers(players: List<PlayerEntity>): Completable {
        return localSource.insertPlayers(players)
    }

}