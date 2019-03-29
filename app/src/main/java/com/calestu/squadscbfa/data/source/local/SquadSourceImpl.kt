package com.calestu.squadscbfa.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.calestu.squadscbfa.data.entity.PlayerEntity
import com.calestu.squadscbfa.data.entity.PlayerSquadEntity
import com.calestu.squadscbfa.data.entity.SquadEntity
import com.calestu.squadscbfa.data.model.SquadAllPlayers
import com.calestu.squadscbfa.data.model.type.PlayerPositionType
import com.calestu.squadscbfa.ui.module.player.model.PlayerItemModelView
import com.calestu.squadscbfa.util.ext.dateTimeNow
import io.reactivex.Completable
import io.reactivex.Flowable
import java.util.*

class SquadSourceImpl(
    private val localDatabase: LocalDatabase
) : SquadSource {

    override fun getMySquads(ownerUid: String): DataSource.Factory<Int, SquadEntity> =
        localDatabase.squadDao().getMySquads(ownerUid)

    override fun getMySquad(entryid: String): LiveData<SquadAllPlayers> =
        localDatabase.squadDao().getMySquad(entryid)

    override fun insertMySquad(squadEntity: SquadEntity): Completable =
        localDatabase.squadDao().insertMySquad(squadEntity)

    override fun updateMySquad(squadEntity: SquadEntity): Completable =
        localDatabase.squadDao().updateMySquad(squadEntity.copy(updatedTime = Date().dateTimeNow()))

    override fun insertPlayerSquad(playerSquadEntity: PlayerSquadEntity): Completable =
        localDatabase.squadDao().insertPlayerSquad(playerSquadEntity)

    override fun getPlayersSquad(squadEntryId: String, positionType: PlayerPositionType): LiveData<List<PlayerEntity>> =
        localDatabase.squadDao().getPlayersSquad(squadEntryId, positionType)

}