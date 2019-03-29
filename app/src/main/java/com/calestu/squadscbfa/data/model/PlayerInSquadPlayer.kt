package com.calestu.squadscbfa.data.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Relation
import com.calestu.squadscbfa.data.entity.PlayerEntity
import com.calestu.squadscbfa.data.entity.PlayerSquadEntity
import com.calestu.squadscbfa.data.entity.SquadEntity

class PlayerInSquadPlayer {

    @Embedded
    lateinit var playerSquad: PlayerSquadEntity

    @ColumnInfo(name = "entryid")
    var player: PlayerEntity? = null

}