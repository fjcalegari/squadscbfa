package com.calestu.squadscbfa.data.repository

import com.calestu.squadscbfa.data.entity.CoachEntity
import io.reactivex.Completable
import io.reactivex.Single

interface CoachRepository {

    fun fetchFromRemote(): Single<Boolean>

    fun insertLocalCoaches(coaches: List<CoachEntity>) : Completable

}