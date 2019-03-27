package com.calestu.squadscbfa.data.mapper

import com.calestu.squadscbfa.data.entity.PlayerEntity
import com.calestu.squadscbfa.data.entity.PlayerSquadEntity
import com.calestu.squadscbfa.data.model.type.ClubType
import com.calestu.squadscbfa.data.model.type.PlayerPositionFormationType
import com.calestu.squadscbfa.data.model.type.PlayerPositionType
import com.calestu.squadscbfa.data.source.remote.model.PlayerRemoteModel
import com.calestu.squadscbfa.ui.module.player.model.PlayerItemModelView

fun PlayerRemoteModel.toEntity(clubType: ClubType) = PlayerEntity(
    entryid = entryid,
    name = name,
    active = status == 1,
    club = clubType,
    pos = PlayerPositionType.getPlayerPosition(pos)
)

fun PlayerEntity.toPlayerItemModelView() = PlayerItemModelView(
    entryid = entryid,
    name = name,
    club = club,
    pos = pos
)

fun PlayerItemModelView.toEntity(squadEntryId: String, playerPositionFormationType: PlayerPositionFormationType) = PlayerSquadEntity(
    player = entryid,
    squad = squadEntryId,
    posFormation = playerPositionFormationType
)