package com.calestu.squadscbfa.data.model

import com.calestu.squadscbfa.data.model.type.PlayerPositionType

data class PlayerSquadModel(
    val playerModel: PlayerModel? = null,
    val playerPositionType: PlayerPositionType
) {

    val labelPos: String
        get() = playerPositionType.tag

}