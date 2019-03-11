package com.calestu.squadscbfa.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.calestu.squadscbfa.data.entity.AppInfoEntity
import com.calestu.squadscbfa.data.entity.ClubEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
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

    //CLUB
    @Query("SELECT * FROM club")
    fun getClubs(): Flowable<List<ClubEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertClubs(clubs : List<ClubEntity>) : Completable

}