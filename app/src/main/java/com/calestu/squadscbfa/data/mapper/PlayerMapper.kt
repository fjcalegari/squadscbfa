package com.calestu.squadscbfa.data.mapper

import com.calestu.squadscbfa.data.entity.PlayerEntity
import com.calestu.squadscbfa.data.model.PlayerModel
import com.google.firebase.database.DataSnapshot

fun DataSnapshot.toListPlayerModel() : List<PlayerModel> {
    if (exists()) {
        children.mapNotNull {it.toPlayerModel()}
    }
    return emptyList()
}

fun DataSnapshot.toPlayerModel() : PlayerModel {
    if (exists()) {
        return getValue(PlayerModel::class.java) ?: PlayerModel()
    }
    return PlayerModel()
}

fun PlayerModel.toEntity() = PlayerEntity (
    entryid = entryid,
    club = club,
    name = name,
    pos = pos,
    active = (club>0)
)

fun PlayerEntity.fromEntity() : PlayerModel {
  return PlayerModel(
      entryid = entryid,
      club = club,
      name = name,
      pos = pos
  )
}