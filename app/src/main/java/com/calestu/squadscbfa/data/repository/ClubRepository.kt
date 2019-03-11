package com.calestu.squadscbfa.data.repository

import com.calestu.squadscbfa.data.entity.ClubEntity
import io.reactivex.Completable
import io.reactivex.Flowable

interface ClubRepository {

    fun insertLocalClubs() : Completable

    fun getClubs(): Flowable<List<ClubEntity>>

}