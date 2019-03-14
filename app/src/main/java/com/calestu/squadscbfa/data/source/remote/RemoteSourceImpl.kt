package com.calestu.squadscbfa.data.source.remote

import com.calestu.squadscbfa.data.entity.ClubEntity
import com.calestu.squadscbfa.data.mapper.toAppInfoModel
import com.calestu.squadscbfa.data.mapper.toListCoachModel
import com.calestu.squadscbfa.data.mapper.toListPlayerModel
import com.calestu.squadscbfa.data.model.AppInfoModel
import com.calestu.squadscbfa.data.model.CoachModel
import com.calestu.squadscbfa.data.model.PlayerModel
import com.calestu.squadscbfa.util.ext.log
import com.google.firebase.database.DataSnapshot
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteSourceImpl @Inject constructor(
    private val firebaseRemoteSource: FirebaseRemoteSource
): RemoteSource {

    override fun getAppInfo(): Single<AppInfoModel> {
        return firebaseRemoteSource.observeSingleValueEvent(DatabaseChildType.VERSION)
            .map {
                Timber.d("getAppInfo.map.Thread.name: ${Thread.currentThread().name}")
                it.toAppInfoModel()
            }
    }

    override fun getClubs(): Flowable<List<ClubEntity>> {
        return Flowable.just(listOf())
    }

    override fun getPlayers(): Single<List<PlayerModel>> {
        return firebaseRemoteSource.observeSingleValueEvent(DatabaseChildType.PLAYERS)
            .map{
                Timber.d("getPlayers.map.Thread.name: ${Thread.currentThread().name}")
                it.toListPlayerModel()}
    }

    override fun getCoaches(): Single<List<CoachModel>> {
        return firebaseRemoteSource.observeSingleValueEvent(DatabaseChildType.COACH)
            .map {
                Timber.d("getCoaches.map.Thread.name: ${Thread.currentThread().name}")
                it.toListCoachModel()
            }
    }

}