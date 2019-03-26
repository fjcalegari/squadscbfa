package com.calestu.squadscbfa.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.calestu.squadscbfa.data.model.type.ClubType

@Entity(tableName = "coach")
data class CoachEntity(

    @PrimaryKey
    val entryid: Int,

    val club: ClubType,

    val name: String,

    val active: Boolean

)