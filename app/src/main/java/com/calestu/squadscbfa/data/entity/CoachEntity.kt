package com.calestu.squadscbfa.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coach")
data class CoachEntity(

    @PrimaryKey @ColumnInfo(name = "entryid")
    var entryid: Int,

    @ColumnInfo(name = "club")
    var club: Int,

    @ColumnInfo(name = "name")
    var name: String

)