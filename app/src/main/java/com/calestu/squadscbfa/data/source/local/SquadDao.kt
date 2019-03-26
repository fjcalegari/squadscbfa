package com.calestu.squadscbfa.data.source.local

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Query
import com.calestu.squadscbfa.data.entity.SquadEntity

@Dao
interface SquadDao {

    @Query("SELECT * FROM squad WHERE owner == :ownerUid ORDER BY updatedTime DESC")
    fun getMySquads(ownerUid: String): DataSource.Factory<Int, SquadEntity>

}