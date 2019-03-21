package com.calestu.squadscbfa.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.calestu.squadscbfa.data.model.type.FormationType
import com.calestu.squadscbfa.data.model.type.PlayerPositionType

@Entity(tableName = "player")
data class PlayerEntity(

    @PrimaryKey @ColumnInfo(name = "entryid")
    val entryid: Int,

    @ColumnInfo(name = "club")
    val club: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "pos")
    val pos: PlayerPositionType,

    @ColumnInfo(name = "active")
    val active: Boolean = false

)