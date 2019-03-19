package com.calestu.squadscbfa.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.calestu.squadscbfa.data.model.type.FormationType
import com.calestu.squadscbfa.data.model.type.RoundType
import com.calestu.squadscbfa.util.ext.dateTimeNow
import java.util.*

@Entity(tableName = "currentsquad")
data class CurrentSquadEntity(

    @PrimaryKey @ColumnInfo(name = "entryid")
    var entryid: String = UUID.randomUUID().toString(),

    @ColumnInfo(name = "created_time")
    var createdTime: Long = Date().dateTimeNow(),

    @ColumnInfo(name = "formation")
    var formation: Int = FormationType.FORMATION_433.index,

    @ColumnInfo(name = "round")
    var round: Int = RoundType.ROUND_1.round

)