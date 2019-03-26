package com.calestu.squadscbfa.data.model

import com.calestu.squadscbfa.data.model.type.PlayerPositionFormationType

data class PlayerSquadModel(
    val playerModel: PlayerModel? = null,
    val playerPositionFormationType: PlayerPositionFormationType
) {

    val labelPos: String
        get() = playerPositionFormationType.playerPositionType.tag

}