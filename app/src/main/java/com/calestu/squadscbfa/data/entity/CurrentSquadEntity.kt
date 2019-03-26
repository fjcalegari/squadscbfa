package com.calestu.squadscbfa.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.calestu.squadscbfa.data.model.type.FormationType
import com.calestu.squadscbfa.data.model.type.RoundType
import com.calestu.squadscbfa.util.ext.dateTimeNow
import java.util.*

@Entity(tableName = "currentsquad")
data class CurrentSquadEntity(

    @PrimaryKey
    val entryid: String = UUID.randomUUID().toString(),

    @ColumnInfo(name = "created_time")
    val createdTime: Long = Date().dateTimeNow(),

    @ColumnInfo(name = "updated_time")
    var updatedTime: Long? = null,

    var title: String,

    var formation: FormationType,

    var round: RoundType

)