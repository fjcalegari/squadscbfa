package com.calestu.squadscbfa.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.calestu.squadscbfa.data.entity.AppVersionEntity
import com.calestu.squadscbfa.data.entity.CoachEntity
import com.calestu.squadscbfa.data.entity.CurrentSquadEntity
import com.calestu.squadscbfa.data.entity.PlayerEntity
import com.calestu.squadscbfa.data.model.type.PlayerPositionType
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface LocalDao {

    //APP VERSION
    @Query("SELECT COUNT(*) FROM appversion")
    fun getCountAppVersion(): Single<Int>

    @Query("SELECT * FROM appversion LIMIT 1")
    fun getAppVersion(): Single<AppVersionEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAppVersion(appVersionEntity: AppVersionEntity) : Completable

    @Update
    fun updateAppVersion(appVersionEntity: AppVersionEntity) : Completable

    //COACH
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveCoach(coachEntity: CoachEntity): Completable

    //PLAYER
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun savePlayer(player: PlayerEntity): Completable

    @Query("SELECT * FROM player WHERE pos == :positionType AND active == 1 ORDER BY name ASC LIMIT 10")
    fun getPlayers(positionType: PlayerPositionType): LiveData<List<PlayerEntity>>

    //CURRENT SQUAD
    @Query("SELECT COUNT(*) FROM currentsquad")
    fun getCountCurrentSquad(): Single<Int>

    @Query("SELECT * FROM currentsquad LIMIT 1")
    fun currentSquad(): Flowable<CurrentSquadEntity>

    @Query("SELECT * FROM currentsquad LIMIT 1")
    fun getLocalCurrentSquad(): Single<CurrentSquadEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCurrentSquad(currentSquadEntity: CurrentSquadEntity) : Completable

    @Update
    fun updateCurrentSquad(currentSquadEntity: CurrentSquadEntity) : Completable

}