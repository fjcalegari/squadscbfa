package com.calestu.squadscbfa.data.mapper

import com.calestu.squadscbfa.data.entity.CoachEntity
import com.calestu.squadscbfa.data.model.CoachModel
import com.google.firebase.database.DataSnapshot

fun DataSnapshot.toListCoachModel() : List<CoachModel> {
    if (exists()) {
        return children.mapNotNull {it.getValue(CoachModel::class.java)}
    }
    return emptyList()
}

fun CoachModel.toEntity() = CoachEntity (
    entryid = entryid,
    club = club,
    name = name,
    active = (club>0)
)

fun CoachEntity.fromEntity() : CoachModel {
    return CoachModel(
        entryid = entryid,
        club = club,
        name = name
    )
}