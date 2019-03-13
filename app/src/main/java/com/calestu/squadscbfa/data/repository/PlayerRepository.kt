package com.calestu.squadscbfa.data.repository

import com.calestu.squadscbfa.data.entity.PlayerEntity
import com.calestu.squadscbfa.data.model.PlayerModel
import io.reactivex.Completable
import io.reactivex.Single

interface PlayerRepository {

    fun fetchFromRemote(): Single<Boolean>

    fun insertLocalPlayers(players: List<PlayerEntity>) : Completable
}