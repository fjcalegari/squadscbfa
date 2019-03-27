package com.calestu.squadscbfa.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.calestu.squadscbfa.data.entity.PlayerSquadEntity
import com.calestu.squadscbfa.data.entity.SquadEntity
import com.calestu.squadscbfa.data.model.type.PlayerPositionFormationType
import com.calestu.squadscbfa.ui.module.mysquad.model.MySquadItemModelView
import com.calestu.squadscbfa.ui.module.player.model.PlayerItemModelView
import com.calestu.squadscbfa.ui.module.squad.edit.model.SquadEditModelView
import io.reactivex.Completable

interface SquadRepository {

    fun getMySquads(ownerUid: String): DataSource.Factory<Int, MySquadItemModelView>

    fun getMySquad(entryid: String): LiveData<SquadEditModelView>

    fun insertMySquad(squadEntity: SquadEntity) : Completable

    fun updateMySquad(squadEditModelView: SquadEditModelView) : Completable

    fun insertPlayerSquad(
        squadEntryId: String,
        playerPositionFormationType: PlayerPositionFormationType,
        playerItemModelView: PlayerItemModelView
    ) : Completable

    fun getPlayersSquad(squadEntryId: String): LiveData<List<PlayerItemModelView>>

}