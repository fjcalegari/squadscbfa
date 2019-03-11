package com.calestu.squadscbfa.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "appinfo")
data class AppInfoEntity(

    @PrimaryKey @ColumnInfo(name = "entryid")
    var entryid: String = UUID.randomUUID().toString(),

    @ColumnInfo(name = "first_open_time")
    var firstOpenTime: Long,

    @ColumnInfo(name = "app_version")
    var appVersion: Int,

    @ColumnInfo(name = "players_version")
    var playersVersion: Int,

    @ColumnInfo(name = "coaches_version")
    var coachesVersion: Int

)