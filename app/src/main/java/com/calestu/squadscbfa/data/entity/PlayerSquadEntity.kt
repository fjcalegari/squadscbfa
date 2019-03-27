package com.calestu.squadscbfa.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.calestu.squadscbfa.data.model.type.PlayerPositionFormationType

@Entity(
    tableName = "squadplayers",
    foreignKeys = [
        ForeignKey(
            entity = SquadEntity::class,
            parentColumns = arrayOf("entryid"),
            childColumns = arrayOf("squad"),
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = PlayerEntity::class,
            parentColumns = arrayOf("entryid"),
            childColumns = arrayOf("player")
        )
    ],
    indices = [Index(value = ["squad", "player", "posFormation"], unique = true)]
)
data class PlayerSquadEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val squad: String,

    val player: Int,

    val posFormation: PlayerPositionFormationType,

    val captain: Boolean = false

)