package com.calestu.squadscbfa.data.source.local

import androidx.paging.DataSource
import com.calestu.squadscbfa.data.entity.SquadEntity

class SquadSourceImpl(private val localDatabase: LocalDatabase) :
    SquadSource {

    override fun getMySquads(ownerUid: String): DataSource.Factory<Int, SquadEntity> {
        return localDatabase.squadDao().getMySquads(ownerUid)
    }

}