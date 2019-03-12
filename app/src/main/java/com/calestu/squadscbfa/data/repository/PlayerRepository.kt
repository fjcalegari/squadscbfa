package com.calestu.squadscbfa.data.repository

import com.calestu.squadscbfa.data.model.PlayerModel
import io.reactivex.Single

interface PlayerRepository {

    fun getRemotePlayers(): Single<List<PlayerModel>>

}