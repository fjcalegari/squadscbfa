package com.calestu.squadscbfa.data.mapper

import com.calestu.squadscbfa.data.entity.ClubEntity
import com.calestu.squadscbfa.data.model.ClubModel

fun ClubModel.toEntities() : List<ClubEntity> {
    return ClubModel.ClubModelEnum.getAll().map { toEntity(it) }
}

fun ClubModel.toEntity(clubModelEnum: ClubModel.ClubModelEnum) : ClubEntity {
    return ClubEntity(
        index = clubModelEnum.index,
        nameTitle = clubModelEnum.nameTitle,
        tag = clubModelEnum.tag
    )
}