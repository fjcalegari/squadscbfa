package com.calestu.squadscbfa.data.source.local

import com.calestu.squadscbfa.data.entity.AppInfoEntity
import com.calestu.squadscbfa.data.entity.ClubEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
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
        return localDatabase
            .localDao()
            .insertAppInfo(appInfoEntity)
    }

    override fun getClubs(): Flowable<List<ClubEntity>> {
        return localDatabase
            .localDao()
            .getClubs()
            .onErrorReturn { listOf() }
    }

    override fun insertClubs(clubs: List<ClubEntity>): Completable {
        return localDatabase
            .localDao()
            .insertClubs(clubs)
    }

}