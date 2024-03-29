package com.calestu.squadscbfa.data.source.local

import androidx.lifecycle.LiveData
import com.calestu.squadscbfa.data.entity.AppVersionEntity
import com.calestu.squadscbfa.data.entity.CoachEntity
import com.calestu.squadscbfa.data.entity.PlayerEntity
import com.calestu.squadscbfa.data.model.type.PlayerPositionType
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Singleton

@Singleton
class LocalSourceImpl(private val localDatabase: LocalDatabase) :
    LocalSource {

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

    override fun getPlayers(positionType: PlayerPositionType): LiveData<List<PlayerEntity>> {
        return localDatabase.localDao().getPlayers(positionType)
    }

}