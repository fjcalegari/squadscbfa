package com.calestu.squadscbfa.data.mapper

import com.calestu.squadscbfa.data.entity.SquadEntity
import com.calestu.squadscbfa.data.model.SquadWithPlayersDbModel
import com.calestu.squadscbfa.ui.module.mysquad.model.MySquadItemModelView
import com.calestu.squadscbfa.ui.module.squad.edit.model.SquadEditModelView
import com.calestu.squadscbfa.util.ext.dateTimeNow
import com.calestu.squadscbfa.util.ext.formatToViewDateTimeDefaults
import java.util.*

fun SquadEntity.toMySquadItemModelView() : MySquadItemModelView {
    return MySquadItemModelView(
        entryid = entryid,
        updatedTime = Date(updatedTime).formatToViewDateTimeDefaults(),
        title = title,
        formation = formation,
        round = round
    )
}

fun SquadEntity.toSquadEditModelView() : SquadEditModelView {
    return SquadEditModelView(
        entryid = entryid,
        owner = owner,
        createdTime = createdTime,
        title = title,
        formation = formation,
        round = round,
        coachEntryid = coach
    )
}

fun SquadEditModelView.toSquadEntity() : SquadEntity {
    return SquadEntity(
        entryid = entryid,
        owner = owner,
        createdTime = createdTime,
        updatedTime = Date().dateTimeNow(),
        title = title,
        formation = formation,
        round = round
    )
}

fun SquadWithPlayersDbModel.toSquadEditModelView() : SquadEditModelView {
    val squad = SquadEditModelView(
        entryid = squad.entryid,
        owner = squad.owner,
        createdTime = squad.createdTime,
        title = squad.title,
        formation = squad.formation,
        round = squad.round,
        coachEntryid = squad.coach
    )

//    players?.let {list ->
//        list.forEach {
//            if (it.posFormation == PlayerPositionFormationType.GOLEIRO) {
//                squad.playerGol = it
//            }
//        }
//    }

    return squad
}

//fun PlayerSquadEntity.toSquadPlayerEditModelView(): SquadPlayerEditModelView {
//    return SquadPlayerEditModelView(
//        entryid = player.,
//    val club: ClubType,
//    val name: String,
//    val pos: PlayerPositionType,
//    val active: Boolean
//    )
//}