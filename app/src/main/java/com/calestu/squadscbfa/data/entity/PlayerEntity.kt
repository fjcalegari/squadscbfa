package com.calestu.squadscbfa.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.calestu.squadscbfa.data.model.type.ClubType
import com.calestu.squadscbfa.data.model.type.PlayerPositionType

@Entity(tableName = "player")
data class PlayerEntity(

    @PrimaryKey
    val entryid: Int,

    val club: ClubType,

    val name: String,

    val pos: PlayerPositionType,

    val active: Boolean

)