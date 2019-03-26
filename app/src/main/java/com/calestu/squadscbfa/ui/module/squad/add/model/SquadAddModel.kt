package com.calestu.squadscbfa.ui.module.squad.add.model

import com.calestu.squadscbfa.data.model.CoachSquadModel
import com.calestu.squadscbfa.data.model.PlayerSquadModel
import com.calestu.squadscbfa.data.model.type.FormationType
import com.calestu.squadscbfa.data.model.type.PlayerPositionFormationType
import com.calestu.squadscbfa.data.model.type.RoundType

data class SquadAddModel(

    val entryid: String,
    val title: String,
    val round: RoundType,
    val formation: FormationType,
    val coach: CoachSquadModel = CoachSquadModel(),
    val playerGol: PlayerSquadModel = PlayerSquadModel(playerPositionFormationType = PlayerPositionFormationType.GOLEIRO),
    val playerLat1: PlayerSquadModel = PlayerSquadModel(playerPositionFormationType = PlayerPositionFormationType.LAT_1),
    val playerLat2: PlayerSquadModel = PlayerSquadModel(playerPositionFormationType = PlayerPositionFormationType.LAT_2),
    val playerZag1: PlayerSquadModel = PlayerSquadModel(playerPositionFormationType = PlayerPositionFormationType.ZAG_1),
    val playerZag2: PlayerSquadModel = PlayerSquadModel(playerPositionFormationType = PlayerPositionFormationType.ZAG_2),
    val playerZag3: PlayerSquadModel = PlayerSquadModel(playerPositionFormationType = PlayerPositionFormationType.ZAG_3),
    val playerMei1: PlayerSquadModel = PlayerSquadModel(playerPositionFormationType = PlayerPositionFormationType.MEI_1),
    val playerMei2: PlayerSquadModel = PlayerSquadModel(playerPositionFormationType = PlayerPositionFormationType.MEI_2),
    val playerMei3: PlayerSquadModel = PlayerSquadModel(playerPositionFormationType = PlayerPositionFormationType.MEI_3),
    val playerMei4: PlayerSquadModel = PlayerSquadModel(playerPositionFormationType = PlayerPositionFormationType.MEI_4),
    val playerMei5: PlayerSquadModel = PlayerSquadModel(playerPositionFormationType = PlayerPositionFormationType.MEI_5),
    val playerAta1: PlayerSquadModel = PlayerSquadModel(playerPositionFormationType = PlayerPositionFormationType.ATA_1),
    val playerAta2: PlayerSquadModel = PlayerSquadModel(playerPositionFormationType = PlayerPositionFormationType.ATA_2),
    val playerAta3: PlayerSquadModel = PlayerSquadModel(playerPositionFormationType = PlayerPositionFormationType.ATA_3)

)