package com.calestu.squadscbfa.data.mapper

import com.calestu.squadscbfa.data.entity.PlayerEntity
import com.calestu.squadscbfa.data.model.type.ClubType
import com.calestu.squadscbfa.data.model.type.PlayerPositionType
import com.calestu.squadscbfa.data.source.remote.model.PlayerRemoteModel

fun PlayerRemoteModel.toEntity(clubType: ClubType) = PlayerEntity(
    entryid = entryid,
    name = name,
    active = status == 1,
    club = clubType,
    pos = PlayerPositionType.getPlayerPosition(pos)
)