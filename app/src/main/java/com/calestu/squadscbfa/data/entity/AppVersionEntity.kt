package com.calestu.squadscbfa.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.calestu.squadscbfa.data.model.type.RoundType
import com.calestu.squadscbfa.util.ext.dateTimeNow
import java.util.*

@Entity(tableName = "appversion")
data class AppVersionEntity(

    @PrimaryKey
    val entryid: String = UUID.randomUUID().toString(),

    val firstOpenTime: Long = Date().dateTimeNow(),

    @ColumnInfo(name = "app")
    var app: Int = 0,

    var forceup: Boolean = false,

    var round: RoundType = RoundType.ROUND_1,

    @ColumnInfo(name = "coach")
    var coach: Int = 0,

    @ColumnInfo(name = "CAP")
    var CAP: Int = 0,

    @ColumnInfo(name = "CAM")
    var CAM: Int = 0,

    @ColumnInfo(name = "AVA")
    var AVA: Int = 0,

    @ColumnInfo(name = "BAH")
    var BAH: Int = 0,

    @ColumnInfo(name = "BOT")
    var BOT: Int = 0,

    @ColumnInfo(name = "CSA")
    var CSA: Int = 0,

    @ColumnInfo(name = "CEA")
    var CEA: Int = 0,

    @ColumnInfo(name = "CHA")
    var CHA: Int = 0,

    @ColumnInfo(name = "COR")
    var COR: Int = 0,

    @ColumnInfo(name = "CRU")
    var CRU: Int = 0,

    @ColumnInfo(name = "FLA")
    var FLA: Int = 0,

    @ColumnInfo(name = "FLU")
    var FLU: Int = 0,

    @ColumnInfo(name = "FOR")
    var FOR: Int = 0,

    @ColumnInfo(name = "GOI")
    var GOI: Int = 0,

    @ColumnInfo(name = "GRE")
    var GRE: Int = 0,

    @ColumnInfo(name = "INT")
    var INT: Int = 0,

    @ColumnInfo(name = "PAL")
    var PAL: Int = 0,

    @ColumnInfo(name = "SAN")
    var SAN: Int = 0,

    @ColumnInfo(name = "SAO")
    var SAO: Int = 0,

    @ColumnInfo(name = "VAS")
    var VAS: Int = 0

)