package com.calestu.squadscbfa.data.source.local

import androidx.paging.DataSource
import com.calestu.squadscbfa.data.entity.AppVersionEntity
import com.calestu.squadscbfa.data.entity.CoachEntity
import com.calestu.squadscbfa.data.entity.CurrentSquadEntity
import com.calestu.squadscbfa.data.entity.PlayerEntity
import com.calestu.squadscbfa.data.model.type.PlayerPositionType
import com.calestu.squadscbfa.data.source.remote.model.AppVersionRemoteModel
import com.calestu.squadscbfa.ui.module.player.model.PlayerModelView
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface LocalSource {

    // APP VERSION
    fun countAppVersion(): Single<Int>
    fun getAppVersion(): Single<AppVersionEntity>
    fun insertAppVersion(appVersionEntity: AppVersionEntity): Completable
    fun updateAppVersion(appVersionEntity: AppVersionEntity): Completable

    //COACH
    fun saveCoach(coachEntity: CoachEntity): Completable

    //PLAYER
    fun savePlayer(playerEntity: PlayerEntity): Completable
    fun getPlayers(positionType: PlayerPositionType): DataSource.Factory<Int, PlayerModelView>

    // CURRENT SQUAD
    fun countCurrentSquad(): Single<Int>
    fun currentSquad(): Flowable<CurrentSquadEntity>
    fun getLocalCurrentSquad(): Single<CurrentSquadEntity>
    fun insertCurrentSquad(currentSquadEntity: CurrentSquadEntity): Completable
    fun updateCurrentSquad(currentSquadEntity: CurrentSquadEntity): Completable

}