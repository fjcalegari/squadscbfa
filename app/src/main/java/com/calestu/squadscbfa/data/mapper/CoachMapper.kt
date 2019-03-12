package com.calestu.squadscbfa.data.mapper

import com.calestu.squadscbfa.data.entity.CoachEntity
import com.calestu.squadscbfa.data.model.CoachModel
import com.google.firebase.database.DataSnapshot

fun DataSnapshot.toCoachModel() : CoachModel {
    if (exists()) {
        return getValue(CoachModel::class.java) ?: CoachModel()
    }
    return CoachModel()
}

fun CoachModel.toEntity() = CoachEntity (
    entryid = entryid,
    club = club,
    name = name
)

fun CoachEntity.fromEntity() : CoachModel {
  return CoachModel(
      entryid = entryid,
      club = club,
      name = name
  )
}