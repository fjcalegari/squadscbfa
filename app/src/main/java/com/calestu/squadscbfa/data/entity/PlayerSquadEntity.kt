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
    indices = [Index(value = ["squad", "player"], unique = true)]
)
data class PlayerSquadEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long,

    val player: Int,

    val squad: String,

    val posFormation: PlayerPositionFormationType,

    val captain: Boolean = false

)