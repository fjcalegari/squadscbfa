package com.calestu.squadscbfa.data.model

import androidx.room.Embedded
import androidx.room.Relation
import com.calestu.squadscbfa.data.entity.PlayerSquadEntity
import com.calestu.squadscbfa.data.entity.SquadEntity

class SquadAllPlayers {

    @Embedded
    lateinit var squad: SquadEntity

    @Relation(parentColumn = "entryid", entityColumn = "squad")
    var players: List<PlayerSquadEntity> = arrayListOf()

}