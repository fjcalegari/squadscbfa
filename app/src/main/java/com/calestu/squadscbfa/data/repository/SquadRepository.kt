package com.calestu.squadscbfa.data.repository

import androidx.paging.DataSource
import com.calestu.squadscbfa.data.entity.CurrentSquadEntity
import com.calestu.squadscbfa.ui.module.mysquad.model.MySquadItemModelView
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface SquadRepository {

    fun getMySquads(ownerUid: String): DataSource.Factory<Int, MySquadItemModelView>

}