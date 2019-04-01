package com.calestu.squadscbfa.data.repository

import androidx.paging.DataSource
import com.calestu.squadscbfa.data.entity.PlayerEntity
import com.calestu.squadscbfa.data.entity.SquadEntity
import com.calestu.squadscbfa.data.mapper.toMySquadItemModelView
import com.calestu.squadscbfa.data.mapper.toSquadEntity
import com.calestu.squadscbfa.data.model.SquadWithPlayersDbModel
import com.calestu.squadscbfa.data.model.type.PlayerPositionType
import com.calestu.squadscbfa.data.source.local.LocalSource
import com.calestu.squadscbfa.data.source.local.SquadSource
import com.calestu.squadscbfa.ui.module.mysquad.model.MySquadItemModelView
import com.calestu.squadscbfa.ui.module.squad.edit.model.SquadEditModelView
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class SquadRepositoryImpl @Inject constructor(
    private val localSource: LocalSource,
    private val squadSource: SquadSource
) : SquadRepository {

    override fun getMySquads(ownerUid: String): DataSource.Factory<Int, MySquadItemModelView> {
        return squadSource.getMySquads(ownerUid).map { it.toMySquadItemModelView() }
    }

    override fun getMySquad(entryid: String): Single<SquadWithPlayersDbModel> {
        return squadSource
            .getMySquad(entryid)
    }

    override fun insertMySquad(squadEntity: SquadEntity): Single<String> {
        return squadSource.insertMySquad(squadEntity).map { squadEntity.entryid }
    }

    override fun updateMySquad(squadEditModelView: SquadEditModelView): Completable {
        return squadSource.updateMySquad(squadEditModelView.toSquadEntity())
    }

    override fun getPlayersByPosition(playerPositionType: PlayerPositionType): Single<List<PlayerEntity>> {
        return squadSource.getPlayersByPosition(playerPositionType)
    }

}