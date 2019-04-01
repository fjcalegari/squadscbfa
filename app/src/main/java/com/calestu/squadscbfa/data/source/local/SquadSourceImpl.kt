package com.calestu.squadscbfa.data.source.local

import androidx.paging.DataSource
import com.calestu.squadscbfa.data.entity.PlayerEntity
import com.calestu.squadscbfa.data.entity.PlayerSquadEntity
import com.calestu.squadscbfa.data.entity.SquadEntity
import com.calestu.squadscbfa.data.model.SquadWithPlayersDbModel
import com.calestu.squadscbfa.data.model.type.PlayerPositionType
import com.calestu.squadscbfa.util.ext.dateTimeNow
import io.reactivex.Completable
import io.reactivex.Single
import java.util.*

class SquadSourceImpl(
    private val localDatabase: LocalDatabase
) : SquadSource {

    override fun getMySquads(ownerUid: String): DataSource.Factory<Int, SquadEntity> =
        localDatabase.squadDao().getMySquads(ownerUid)

    override fun insertMySquad(squadEntity: SquadEntity): Single<Long> {
        return Single.fromCallable {
            localDatabase
                .squadDao()
                .insertMySquad(squadEntity)
        }
    }

    override fun getMySquad(entryid: String): Single<SquadWithPlayersDbModel> {
        return localDatabase
            .squadDao()
            .getMySquad(entryid)
    }

    override fun updateMySquad(squadEntity: SquadEntity): Completable =
        localDatabase.squadDao().updateMySquad(squadEntity.copy(updatedTime = Date().dateTimeNow()))

    override fun insertPlayerSquad(playerSquadEntity: PlayerSquadEntity): Completable =
        localDatabase.squadDao().insertPlayerSquad(playerSquadEntity)

    override fun updatePlayerSquad(playerSquadEntity: PlayerSquadEntity): Completable =
        localDatabase.squadDao().updatePlayerSquad(playerSquadEntity)

    override fun deletePlayerSquad(player: PlayerSquadEntity): Completable =
        localDatabase.squadDao().deletePlayerSquad(player)

    override fun getPlayersByPosition(playerPositionType: PlayerPositionType): Single<List<PlayerEntity>> =
            localDatabase.squadDao().getPlayersByPosition(playerPositionType)

}