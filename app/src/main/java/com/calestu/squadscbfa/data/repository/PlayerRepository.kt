package com.calestu.squadscbfa.data.repository

import androidx.paging.DataSource
import com.calestu.squadscbfa.data.entity.PlayerEntity
import com.calestu.squadscbfa.data.model.AppVersionResultModel
import com.calestu.squadscbfa.data.model.type.PlayerPositionType
import com.calestu.squadscbfa.ui.module.player.model.PlayerItemModelView
import io.reactivex.Completable

interface PlayerRepository {

    fun loadPlayers(appVersionResultModel: AppVersionResultModel): Completable

    fun savePlayer(playerEntity: PlayerEntity): Completable

    fun getPlayers(positionType: PlayerPositionType): DataSource.Factory<Int, PlayerItemModelView>

}