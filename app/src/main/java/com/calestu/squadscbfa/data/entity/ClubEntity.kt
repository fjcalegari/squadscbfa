package com.calestu.squadscbfa.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "club")
data class ClubEntity(

    @PrimaryKey @ColumnInfo(name = "entryid")
    var entryid: String = UUID.randomUUID().toString(),

    @ColumnInfo(name = "index")
    var index: Int,

    @ColumnInfo(name = "nameTitle")
    var nameTitle: String,

    @ColumnInfo(name = "tag")
    var tag: String

)