package com.calestu.squadscbfa.data.source.local

import androidx.paging.DataSource
import com.calestu.squadscbfa.data.entity.AppVersionEntity
import com.calestu.squadscbfa.data.entity.CoachEntity
import com.calestu.squadscbfa.data.entity.CurrentSquadEntity
import com.calestu.squadscbfa.data.entity.PlayerEntity
import com.calestu.squadscbfa.data.model.type.PlayerPositionType
import com.calestu.squadscbfa.util.ext.dateTimeNow
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import java.util.*
import javax.inject.Singleton

@Singleton
class LocalSourceImpl(private val localDatabase: LocalDatabase) :
    LocalSource {

    override fun getLocalCurrentSquad(): Single<CurrentSquadEntity> =
        localDatabase.localDao().getLocalCurrentSquad()

    override fun countCurrentSquad(): Single<Int> =
        localDatabase.localDao().getCountCurrentSquad()

    override fun currentSquad(): Flowable<CurrentSquadEntity> =
        localDatabase.localDao().currentSquad()

    override fun insertCurrentSquad(currentSquadEntity: CurrentSquadEntity): Completable =
        localDatabase.localDao().insertCurrentSquad(currentSquadEntity)

    override fun updateCurrentSquad(currentSquadEntity: CurrentSquadEntity): Completable =
        localDatabase.localDao().updateCurrentSquad(currentSquadEntity.copy(updatedTime = Date().dateTimeNow()))

    override fun countAppVersion(): Single<Int> =
        localDatabase.localDao().getCountAppVersion()

    override fun getAppVersion(): Single<AppVersionEntity> =
        localDatabase.localDao().getAppVersion()

    override fun insertAppVersion(appVersionEntity: AppVersionEntity): Completable =
        localDatabase.localDao().insertAppVersion(appVersionEntity)

    override fun updateAppVersion(appVersionEntity: AppVersionEntity): Completable =
        localDatabase.localDao().updateAppVersion(appVersionEntity)

    override fun saveCoach(coachEntity: CoachEntity): Completable {
        return localDatabase.localDao().saveCoach(coachEntity)
    }

    override fun savePlayer(playerEntity: PlayerEntity): Completable {
        return localDatabase.localDao().savePlayer(playerEntity)
    }

    override fun getPlayers(positionType: PlayerPositionType): DataSource.Factory<Int, PlayerEntity> {
        return localDatabase.localDao().getPlayers(positionType)
    }

}