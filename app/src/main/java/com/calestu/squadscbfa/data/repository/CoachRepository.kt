package com.calestu.squadscbfa.data.repository

import com.calestu.squadscbfa.data.entity.CoachEntity
import io.reactivex.Completable

interface CoachRepository {

    fun loadCoaches(): Completable

    fun saveCoach(coachEntity: CoachEntity): Completable

}