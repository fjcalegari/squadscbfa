package com.calestu.squadscbfa.data.repository

import com.calestu.squadscbfa.data.entity.PlayerEntity
import com.calestu.squadscbfa.data.model.AppVersionResultModel
import io.reactivex.Completable

interface PlayerRepository {

    fun loadPlayers(appVersionResultModel: AppVersionResultModel): Completable

    fun savePlayer(playerEntity: PlayerEntity): Completable

}