package com.calestu.squadscbfa.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.calestu.squadscbfa.data.model.type.FormationType
import com.calestu.squadscbfa.data.model.type.RoundType

@Entity(tableName = "squad")
data class SquadEntity(

    @PrimaryKey
    val entryid: String,

    val owner: String,

    val createdTime: Long,

    var updatedTime: Long,

    var title: String,

    var formation: FormationType,

    var round: RoundType

)