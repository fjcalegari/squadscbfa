package com.calestu.squadscbfa.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.calestu.squadscbfa.data.entity.*
import com.calestu.squadscbfa.ui.module.squad.add.model.SquadAddModel
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface LocalDao {

    //APP INFO
    @Query("SELECT * FROM appinfo LIMIT 1")
    fun getAppInfo(): Single<AppInfoEntity>

    @Query("SELECT COUNT(*) FROM appinfo")
    fun getCountAppInfo(): Single<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAppInfo(appInfoEntity: AppInfoEntity) : Completable

    @Update
    fun updateAppInfo(appInfoEntity: AppInfoEntity) : Completable

    //CLUB
    @Query("SELECT * FROM club")
    fun getClubs(): Flowable<List<ClubEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertClubs(clubs : List<ClubEntity>) : Completable

    //PLAYER
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlayers(players : List<PlayerEntity>) : Completable

    //COACH
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCoaches(coaches : List<CoachEntity>) : Completable

    //CURRENT SQUAD
    @Query("SELECT * FROM currentsquad LIMIT 1")
    fun getCurrentSquad(): LiveData<CurrentSquadEntity>

    @Query("SELECT * FROM currentsquad LIMIT 1")
    fun getLocalCurrentSquad(): Single<CurrentSquadEntity>

    @Query("SELECT COUNT(*) FROM currentsquad")
    fun getCountCurrentSquad(): Single<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCurrentSquad(currentSquadEntity: CurrentSquadEntity) : Completable

    @Update
    fun updateCurrentSquad(currentSquadEntity: CurrentSquadEntity) : Completable

}