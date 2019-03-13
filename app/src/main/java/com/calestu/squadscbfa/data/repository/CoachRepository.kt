package com.calestu.squadscbfa.data.repository

import com.calestu.squadscbfa.data.entity.CoachEntity
import com.calestu.squadscbfa.data.model.CoachModel
import io.reactivex.Completable
import io.reactivex.Single

interface CoachRepository {

    fun getRemoteCoaches(): Single<List<CoachModel>>

    fun insertLocalCoaches(coaches: List<CoachEntity>) : Completable

}