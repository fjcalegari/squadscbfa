package com.calestu.squadscbfa.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.calestu.squadscbfa.data.entity.PlayerSquadEntity
import com.calestu.squadscbfa.data.entity.SquadEntity
import com.calestu.squadscbfa.data.model.SquadAllPlayers
import com.calestu.squadscbfa.ui.module.squad.edit.model.SquadEditModelView
import io.reactivex.Completable

interface SquadSource {

    fun getMySquads(ownerUid: String): DataSource.Factory<Int, SquadEntity>

    fun getMySquad(entryid: String): LiveData<SquadAllPlayers>

    fun insertMySquad(squadEntity: SquadEntity) : Completable

    fun updateMySquad(squadEntity: SquadEntity) : Completable

    fun insertPlayerSquad(playerSquadEntity: PlayerSquadEntity) : Completable

    fun getPlayersSquad(squadEntryId: String): LiveData<List<PlayerSquadEntity>>

}