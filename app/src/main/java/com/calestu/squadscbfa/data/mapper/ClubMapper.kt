package com.calestu.squadscbfa.data.mapper

import com.calestu.squadscbfa.data.entity.ClubEntity
import com.calestu.squadscbfa.data.model.type.ClubType

fun ClubType.toEntity() : ClubEntity {
    return ClubEntity(
        index = index,
        nameTitle = nameTitle,
        tag = tag
    )
}