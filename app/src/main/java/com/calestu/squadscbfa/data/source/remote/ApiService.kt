package com.calestu.squadscbfa.data.source.remote

import com.calestu.squadscbfa.data.source.remote.model.AppVersionRemoteModel
import com.calestu.squadscbfa.data.source.remote.model.CoachRemoteModel
import com.calestu.squadscbfa.data.source.remote.model.PlayerRemoteModel
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {

    //APP VERSION
    @GET
    fun getAppVersion(@Url url: String) : Single<AppVersionRemoteModel>

    //COACH
    @GET
    fun getCoaches(@Url url: String) : Flowable<List<CoachRemoteModel>>

    //PLAYER
    @GET
    fun getPlayers(@Url url: String) : Flowable<List<PlayerRemoteModel>>

}