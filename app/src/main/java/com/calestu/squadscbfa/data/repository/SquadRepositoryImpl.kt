package com.calestu.squadscbfa.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.DataSource
import com.calestu.squadscbfa.data.entity.PlayerEntity
import com.calestu.squadscbfa.data.entity.SquadEntity
import com.calestu.squadscbfa.data.mapper.*
import com.calestu.squadscbfa.data.model.type.PlayerPositionFormationType
import com.calestu.squadscbfa.data.model.type.PlayerPositionType
import com.calestu.squadscbfa.data.source.local.LocalSource
import com.calestu.squadscbfa.data.source.local.SquadSource
import com.calestu.squadscbfa.ui.module.mysquad.model.MySquadItemModelView
import com.calestu.squadscbfa.ui.module.player.model.PlayerItemModelView
import com.calestu.squadscbfa.ui.module.squad.edit.model.SquadEditModelView
import io.reactivex.Completable
import io.reactivex.Flowable
import timber.log.Timber
import javax.inject.Inject

class SquadRepositoryImpl @Inject constructor(
    private val localSource: LocalSource,
    private val squadSource: SquadSource
) : SquadRepository {

    override fun getMySquads(ownerUid: String): DataSource.Factory<Int, MySquadItemModelView> {
        Timber.d("getMySquads: $ownerUid")
        return squadSource.getMySquads(ownerUid).map { it.toMySquadItemModelView() }
    }

    override fun getMySquad(entryid: String): LiveData<SquadEditModelView> {
        Timber.d("getMySquad: $entryid")
        return Transformations.map(squadSource.getMySquad(entryid)) {
            Timber.d("getMySquad.map.squad: ${it.squad}")
            Timber.d("getMySquad.map.players: ${it.players}")
            it.squad.toSquadEditModelView()
        }
    }

    override fun insertPlayerSquad(
        squadEntryId: String,
        playerPositionFormationType: PlayerPositionFormationType,
        playerItemModelView: PlayerItemModelView): Completable =
        squadSource.insertPlayerSquad(playerItemModelView.toEntity(squadEntryId, playerPositionFormationType))

    override fun getPlayersSquad(squadEntryId: String, positionType: PlayerPositionType): LiveData<List<PlayerItemModelView>> {
        return Transformations.map(squadSource.getPlayersSquad(squadEntryId, positionType)) {
            it.map { p -> p.toPlayerItemModelView(true) }
        }
    }

    override fun insertMySquad(squadEntity: SquadEntity): Completable {
        Timber.d("insertMySquad: $squadEntity")
        return squadSource.insertMySquad(squadEntity)
    }

    override fun updateMySquad(squadEditModelView: SquadEditModelView): Completable {
        Timber.d("updateMySquad: $squadEditModelView")
        return squadSource.updateMySquad(squadEditModelView.toSquadEntity())
    }
}