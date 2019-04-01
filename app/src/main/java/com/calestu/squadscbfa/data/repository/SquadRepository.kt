package com.calestu.squadscbfa.data.repository

import androidx.paging.DataSource
import com.calestu.squadscbfa.data.entity.PlayerEntity
import com.calestu.squadscbfa.data.entity.SquadEntity
import com.calestu.squadscbfa.data.model.SquadWithPlayersDbModel
import com.calestu.squadscbfa.data.model.type.PlayerPositionType
import com.calestu.squadscbfa.ui.module.mysquad.model.MySquadItemModelView
import com.calestu.squadscbfa.ui.module.squad.edit.model.SquadEditModelView
import io.reactivex.Completable
import io.reactivex.Single

interface SquadRepository {

    fun getMySquads(ownerUid: String): DataSource.Factory<Int, MySquadItemModelView>

    fun getMySquad(entryid: String): Single<SquadWithPlayersDbModel>

    fun insertMySquad(squadEntity: SquadEntity) : Single<String>

    fun updateMySquad(squadEditModelView: SquadEditModelView) : Completable

    fun getPlayersByPosition(playerPositionType: PlayerPositionType): Single<List<PlayerEntity>>

}