package com.calestu.squadscbfa.data.source.remote

import com.calestu.squadscbfa.data.source.remote.model.AppVersionRemoteModel
import com.calestu.squadscbfa.data.source.remote.model.CoachRemoteModel
import com.calestu.squadscbfa.data.source.remote.model.PlayerRemoteModel
import com.calestu.squadscbfa.data.source.remote.model.type.AppVersionRemoteType
import io.reactivex.Flowable
import io.reactivex.Single

interface RemoteSource {

    // APP VERSION
    fun getAppVersion(): Single<AppVersionRemoteModel>

    // COACHES
    fun getCoaches() : Flowable<List<CoachRemoteModel>>

    // PLAYERS
    fun getPlayers(appVersionRemoteType: AppVersionRemoteType) : Flowable<List<PlayerRemoteModel>>

}