package com.calestu.squadscbfa.data.repository

import androidx.paging.DataSource
import com.calestu.squadscbfa.data.mapper.toMySquadItemModelView
import com.calestu.squadscbfa.data.source.local.LocalSource
import com.calestu.squadscbfa.data.source.local.SquadSource
import com.calestu.squadscbfa.ui.module.mysquad.model.MySquadItemModelView
import timber.log.Timber
import javax.inject.Inject

class SquadRepositoryImpl @Inject constructor(
    private val localSource: LocalSource,
    private val squadSource: SquadSource
) : SquadRepository {

    override fun getMySquads(ownerUid: String): DataSource.Factory<Int, MySquadItemModelView> {
        Timber.d("getMySquads: $ownerUid")
        return squadSource.getMySquads(ownerUid).map { it.toMySquadItemModelView() }
    }

}