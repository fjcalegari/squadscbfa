package com.calestu.squadscbfa.data.mapper

import com.calestu.squadscbfa.data.entity.PlayerEntity
import com.calestu.squadscbfa.data.model.PlayerModel
import com.google.firebase.database.DataSnapshot
import timber.log.Timber

fun DataSnapshot.toListPlayerModel() : List<PlayerModel> {
    if (exists()) {
        children.mapNotNull { Timber.d("toListPlayerModel: ${it.toPlayerModelModel()}") }
    }
    return emptyList()
}

fun DataSnapshot.toPlayerModelModel() : PlayerModel {
    if (exists()) {
        return getValue(PlayerModel::class.java) ?: PlayerModel()
    }
    return PlayerModel()
}

fun PlayerModel.toEntity() = PlayerEntity (
    entryid = entryid,
    club = club,
    name = name,
    pos = pos
)

fun PlayerEntity.fromEntity() : PlayerModel {
  return PlayerModel(
      entryid = entryid,
      club = club,
      name = name,
      pos = pos
  )
}