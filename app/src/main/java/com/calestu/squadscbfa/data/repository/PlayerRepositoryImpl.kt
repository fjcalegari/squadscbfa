package com.calestu.squadscbfa.data.repository

import com.calestu.squadscbfa.data.model.PlayerModel
import com.calestu.squadscbfa.data.source.local.LocalSource
import com.calestu.squadscbfa.data.source.remote.RemoteSource
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PlayerRepositoryImpl @Inject constructor(
    private val localSource: LocalSource,
    private val remoteSource: RemoteSource
) : PlayerRepository {

    override fun getRemotePlayers(): Single<List<PlayerModel>> {
        return remoteSource.getPlayers()
    }

}