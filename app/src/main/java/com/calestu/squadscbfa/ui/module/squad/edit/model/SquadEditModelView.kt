package com.calestu.squadscbfa.ui.module.squad.edit.model

import com.calestu.squadscbfa.data.model.type.*

data class SquadEditModelView(

    val entryid: String,
    val owner: String,
    val createdTime: Long,
    val title: String,
    val round: RoundType,
    val formation: FormationType,
    val coachEntryid: Int? = null,
    val coach: SquadCoachEditModelView? = null,
    var playerGol: SquadPlayerPositionEditModelView = SquadPlayerPositionEditModelView(positionFormation = PlayerPositionFormationType.GOLEIRO, playerPosition = PlayerPositionType.GOLEIRO),
    var playerLat1: SquadPlayerPositionEditModelView = SquadPlayerPositionEditModelView(positionFormation = PlayerPositionFormationType.LAT_1, playerPosition = PlayerPositionType.LATERAL),
    var playerLat2: SquadPlayerPositionEditModelView = SquadPlayerPositionEditModelView(positionFormation = PlayerPositionFormationType.LAT_2, playerPosition = PlayerPositionType.LATERAL),
    var playerZag1: SquadPlayerPositionEditModelView = SquadPlayerPositionEditModelView(positionFormation = PlayerPositionFormationType.ZAG_1, playerPosition = PlayerPositionType.ZAGUEIRO),
    var playerZag2: SquadPlayerPositionEditModelView = SquadPlayerPositionEditModelView(positionFormation = PlayerPositionFormationType.ZAG_2, playerPosition = PlayerPositionType.ZAGUEIRO),
    var playerZag3: SquadPlayerPositionEditModelView = SquadPlayerPositionEditModelView(positionFormation = PlayerPositionFormationType.ZAG_3, playerPosition = PlayerPositionType.ZAGUEIRO),
    var playerMei1: SquadPlayerPositionEditModelView = SquadPlayerPositionEditModelView(positionFormation = PlayerPositionFormationType.MEI_1, playerPosition = PlayerPositionType.MEIA),
    var playerMei2: SquadPlayerPositionEditModelView = SquadPlayerPositionEditModelView(positionFormation = PlayerPositionFormationType.MEI_2, playerPosition = PlayerPositionType.MEIA),
    var playerMei3: SquadPlayerPositionEditModelView = SquadPlayerPositionEditModelView(positionFormation = PlayerPositionFormationType.MEI_3, playerPosition = PlayerPositionType.MEIA),
    var playerMei4: SquadPlayerPositionEditModelView = SquadPlayerPositionEditModelView(positionFormation = PlayerPositionFormationType.MEI_4, playerPosition = PlayerPositionType.MEIA),
    var playerMei5: SquadPlayerPositionEditModelView = SquadPlayerPositionEditModelView(positionFormation = PlayerPositionFormationType.MEI_5, playerPosition = PlayerPositionType.MEIA),
    var playerAta1: SquadPlayerPositionEditModelView = SquadPlayerPositionEditModelView(positionFormation = PlayerPositionFormationType.ATA_1, playerPosition = PlayerPositionType.ATACANTE),
    var playerAta2: SquadPlayerPositionEditModelView = SquadPlayerPositionEditModelView(positionFormation = PlayerPositionFormationType.ATA_2, playerPosition = PlayerPositionType.ATACANTE),
    var playerAta3: SquadPlayerPositionEditModelView = SquadPlayerPositionEditModelView(positionFormation = PlayerPositionFormationType.ATA_3, playerPosition = PlayerPositionType.ATACANTE)
)

data class SquadPlayerPositionEditModelView(
    val player: SquadPlayerEditModelView? = null,
    val positionFormation: PlayerPositionFormationType,
    val playerPosition: PlayerPositionType
)

data class SquadPlayerEditModelView(
    val entryid: Int,
    val club: ClubType,
    val name: String,
    val pos: PlayerPositionType,
    val active: Boolean
)

data class SquadCoachEditModelView(
    val entryid: Int,
    val club: ClubType,
    val name: String,
    val active: Boolean
)