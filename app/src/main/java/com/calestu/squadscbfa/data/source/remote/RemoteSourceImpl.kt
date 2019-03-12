package com.calestu.squadscbfa.data.source.remote

import com.calestu.squadscbfa.data.entity.ClubEntity
import com.calestu.squadscbfa.data.mapper.toAppInfoModel
import com.calestu.squadscbfa.data.mapper.toListPlayerModel
import com.calestu.squadscbfa.data.model.AppInfoModel
import com.calestu.squadscbfa.data.model.CoachModel
import com.calestu.squadscbfa.data.model.PlayerModel
import io.reactivex.Flowable
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteSourceImpl @Inject constructor(
): FirebaseRemoteSource(), RemoteSource {

    override fun getAppInfo(): Single<AppInfoModel> {
        return versionChild().observeSingleValueEvent().map { it.toAppInfoModel() }
    }

    override fun getClubs(): Flowable<List<ClubEntity>> {
        return Flowable.just(listOf())
    }

    override fun getPlayers(): Single<List<PlayerModel>> {
        Timber.d("getPlayers: ")
        return playersChild().observeSingleValueEvent().map { it.toListPlayerModel() }
    }

    override fun getCoaches(): Single<List<CoachModel>> {
        Timber.d("getCoaches: ")
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}