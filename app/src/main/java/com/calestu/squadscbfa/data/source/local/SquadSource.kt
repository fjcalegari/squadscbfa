package com.calestu.squadscbfa.data.source.local

import androidx.paging.DataSource
import com.calestu.squadscbfa.data.entity.PlayerEntity
import com.calestu.squadscbfa.data.entity.PlayerSquadEntity
import com.calestu.squadscbfa.data.entity.SquadEntity
import com.calestu.squadscbfa.data.model.SquadWithPlayersDbModel
import com.calestu.squadscbfa.data.model.type.PlayerPositionType
import io.reactivex.Completable
import io.reactivex.Single

interface SquadSource {

    fun getMySquads(ownerUid: String): DataSource.Factory<Int, SquadEntity>

    fun getMySquad(entryid: String): Single<SquadWithPlayersDbModel>

    fun insertMySquad(squadEntity: SquadEntity): Single<Long>

    fun updateMySquad(squadEntity: SquadEntity): Completable

    fun insertPlayerSquad(playerSquadEntity: PlayerSquadEntity): Completable

    fun updatePlayerSquad(playerSquadEntity: PlayerSquadEntity): Completable

    fun deletePlayerSquad(player: PlayerSquadEntity): Completable

    fun getPlayersByPosition(playerPositionType: PlayerPositionType): Single<List<PlayerEntity>>

}