package com.calestu.squadscbfa.data.model

import com.calestu.squadscbfa.data.model.type.PlayerPositionType

data class PlayerModel(
    val entryid: Int,
    val club: Int,
    val name: String,
    val pos: PlayerPositionType
)

