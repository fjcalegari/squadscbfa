package com.calestu.squadscbfa.data.mapper

import com.calestu.squadscbfa.data.entity.CoachEntity
import com.calestu.squadscbfa.data.model.type.ClubType
import com.calestu.squadscbfa.data.source.remote.model.CoachRemoteModel

fun CoachRemoteModel.toEntity() = CoachEntity(
    entryid = entryid,
    name = name,
    active = status == 1,
    club = ClubType.getClub(club)
)