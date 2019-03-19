package com.calestu.squadscbfa.ui.module.squad.add.model

import com.calestu.squadscbfa.data.model.PlayerSquadModel
import com.calestu.squadscbfa.data.model.type.FormationType
import com.calestu.squadscbfa.data.model.type.PlayerPositionType

data class SquadAddModel(
    val formation: FormationType,
    val playerGol: PlayerSquadModel = PlayerSquadModel(playerPositionType = PlayerPositionType.GOLEIRO),
    val playerLat1: PlayerSquadModel = PlayerSquadModel(playerPositionType = PlayerPositionType.LATERAL),
    val playerLat2: PlayerSquadModel = PlayerSquadModel(playerPositionType = PlayerPositionType.LATERAL),
    val playerZag1: PlayerSquadModel = PlayerSquadModel(playerPositionType = PlayerPositionType.ZAGUEIRO),
    val playerZag2: PlayerSquadModel = PlayerSquadModel(playerPositionType = PlayerPositionType.ZAGUEIRO),
    val playerZag3: PlayerSquadModel = PlayerSquadModel(playerPositionType = PlayerPositionType.ZAGUEIRO),
    val playerMei1: PlayerSquadModel = PlayerSquadModel(playerPositionType = PlayerPositionType.MEIA),
    val playerMei2: PlayerSquadModel = PlayerSquadModel(playerPositionType = PlayerPositionType.MEIA),
    val playerMei3: PlayerSquadModel = PlayerSquadModel(playerPositionType = PlayerPositionType.MEIA),
    val playerMei4: PlayerSquadModel = PlayerSquadModel(playerPositionType = PlayerPositionType.MEIA),
    val playerMei5: PlayerSquadModel = PlayerSquadModel(playerPositionType = PlayerPositionType.MEIA),
    val playerAta1: PlayerSquadModel = PlayerSquadModel(playerPositionType = PlayerPositionType.ATACANTE),
    val playerAta2: PlayerSquadModel = PlayerSquadModel(playerPositionType = PlayerPositionType.ATACANTE),
    val playerAta3: PlayerSquadModel = PlayerSquadModel(playerPositionType = PlayerPositionType.ATACANTE)
)