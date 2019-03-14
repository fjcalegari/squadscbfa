package com.calestu.squadscbfa.data.source.local

import com.calestu.squadscbfa.data.entity.AppInfoEntity
import com.calestu.squadscbfa.data.entity.ClubEntity
import com.calestu.squadscbfa.data.entity.CoachEntity
import com.calestu.squadscbfa.data.entity.PlayerEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Singleton

@Singleton
class LocalSourceImpl(private val localDatabase: LocalDatabase) :
    LocalSource {

    override fun getAppInfo(): Single<AppInfoEntity> {
        return localDatabase
            .localDao()
            .getAppInfo()
    }

    override fun getCountAppInfo(): Single<Int> {
        return localDatabase
            .localDao()
            .getCountAppInfo()
    }

    override fun insertAppInfo(appInfoEntity: AppInfoEntity): Completable {
        Timber.d("insertAppInfo.Thread.name: ${Thread.currentThread().name}")
        return localDatabase
            .localDao()
            .insertAppInfo(appInfoEntity)
    }

    override fun updateAppInfo(appInfoEntity: AppInfoEntity): Completable {
        Timber.d("updateAppInfo.Thread.name: ${Thread.currentThread().name}")
        return localDatabase
            .localDao()
            .updateAppInfo(appInfoEntity)
    }

    override fun getClubs(): Flowable<List<ClubEntity>> {
        return localDatabase
            .localDao()
            .getClubs()
            .onErrorReturn { listOf() }
    }

    override fun insertClubs(clubs: List<ClubEntity>): Completable {
        Timber.d("insertClubs.Thread.name: ${Thread.currentThread().name}")
        return localDatabase
            .localDao()
            .insertClubs(clubs)
    }

    override fun insertPlayers(players: List<PlayerEntity>): Completable {
        Timber.d("insertPlayers: $players")
        Timber.d("insertPlayers.Thread.name: ${Thread.currentThread().name}")
        return localDatabase
            .localDao()
            .insertPlayers(players)
    }

    override fun insertCoaches(coaches: List<CoachEntity>): Completable {
        Timber.d("insertCoaches: $coaches")
        Timber.d("insertCoaches.Thread.name: ${Thread.currentThread().name}")
        return localDatabase
            .localDao()
            .insertCoaches(coaches)
    }

}