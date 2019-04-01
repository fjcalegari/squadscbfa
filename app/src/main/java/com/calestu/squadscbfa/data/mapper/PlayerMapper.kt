package com.calestu.squadscbfa.data.mapper

import com.calestu.squadscbfa.data.entity.PlayerEntity
import com.calestu.squadscbfa.data.model.type.ClubType
import com.calestu.squadscbfa.data.model.type.PlayerPositionFormationType
import com.calestu.squadscbfa.data.model.type.PlayerPositionType
import com.calestu.squadscbfa.data.source.remote.model.PlayerRemoteModel
import com.calestu.squadscbfa.ui.module.player.model.PlayerItemModelView
import com.calestu.squadscbfa.ui.module.player.model.PlayerItemModelViewStatus

fun PlayerRemoteModel.toEntity(clubType: ClubType) = PlayerEntity(
    entryid = entryid,
    name = name,
    active = status == 1,
    club = clubType,
    pos = PlayerPositionType.getPlayerPosition(pos)
)

fun List<PlayerEntity>.toListPlayerItemModelView(): List<PlayerItemModelView> {
    return this.map { it.toPlayerItemModelView() }
}

fun PlayerEntity.toPlayerItemModelView()= PlayerItemModelView(
    entryid = entryid,
    name = name,
    club = club,
    position = pos,
    status = PlayerItemModelViewStatus.NOT_SELECTED
)

fun PlayerPositionFormationType.getPlayerPositionType() : PlayerPositionType {
    if (this == PlayerPositionFormationType.LAT_1 ||
        this == PlayerPositionFormationType.LAT_2
    ) {
        return PlayerPositionType.LATERAL

    } else if (this == PlayerPositionFormationType.ZAG_1 ||
        this == PlayerPositionFormationType.ZAG_2 ||
        this == PlayerPositionFormationType.ZAG_3
    ) {
        return PlayerPositionType.ZAGUEIRO

    } else if (this == PlayerPositionFormationType.MEI_1 ||
        this == PlayerPositionFormationType.MEI_2 ||
        this == PlayerPositionFormationType.MEI_3 ||
        this == PlayerPositionFormationType.MEI_4 ||
        this == PlayerPositionFormationType.MEI_5
    ) {
        return PlayerPositionType.MEIA

    } else if (this == PlayerPositionFormationType.ATA_1 ||
        this == PlayerPositionFormationType.ATA_2 ||
        this == PlayerPositionFormationType.ATA_3
    ) {
        return PlayerPositionType.ATACANTE

    } else {
        return PlayerPositionType.GOLEIRO
    }
}

//fun PlayersWithSquadModel.toEntity(squadEntryId: String) = PlayerSquadEntity(
//    player = entryid,
//    squad = squadEntryId,
//    posFormation = posFormation!!,
//    club = club,
//    pos = pos,
//    name = name,
//    captain = false
//)
//
//fun PlayersWithSquadModel.toEntityDelete() = PlayerSquadEntity(
//    id = id!!,
//    entryid = entryid,
//    squad = squad!!,
//    posFormation = posFormation!!,
//    club = club,
//    pos = pos,
//    name = name,
//    captain = captain!!,
//    selected = selected!!
//)