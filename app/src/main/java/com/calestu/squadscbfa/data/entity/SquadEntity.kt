package com.calestu.squadscbfa.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.calestu.squadscbfa.data.model.type.FormationType
import com.calestu.squadscbfa.data.model.type.RoundType

@Entity(
    tableName = "squad",
    foreignKeys = [
        ForeignKey(
            entity = CoachEntity::class,
            parentColumns = arrayOf("entryid"),
            childColumns = arrayOf("coach"),
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["entryid", "owner"], unique = true)]
)
data class SquadEntity(

    @PrimaryKey
    val entryid: String,

    val owner: String,

    val createdTime: Long,

    var updatedTime: Long,

    var title: String,

    var formation: FormationType,

    var round: RoundType,

    val coach: Int? = null

)