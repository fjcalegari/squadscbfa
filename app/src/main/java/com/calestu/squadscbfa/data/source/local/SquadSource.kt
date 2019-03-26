package com.calestu.squadscbfa.data.source.local

import androidx.paging.DataSource
import com.calestu.squadscbfa.data.entity.SquadEntity

interface SquadSource {

    fun getMySquads(ownerUid: String): DataSource.Factory<Int, SquadEntity>

}