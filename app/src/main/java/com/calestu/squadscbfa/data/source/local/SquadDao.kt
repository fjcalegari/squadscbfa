package com.calestu.squadscbfa.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.calestu.squadscbfa.data.entity.PlayerSquadEntity
import com.calestu.squadscbfa.data.entity.SquadEntity
import com.calestu.squadscbfa.data.model.SquadAllPlayers
import io.reactivex.Completable

@Dao
interface SquadDao {

    @Query("SELECT * FROM squad WHERE owner == :ownerUid ORDER BY updatedTime DESC")
    fun getMySquads(ownerUid: String): DataSource.Factory<Int, SquadEntity>

    @Transaction
    @Query("SELECT * FROM squad WHERE entryid == :entryid LIMIT 1")
    fun getMySquad(entryid: String): LiveData<SquadAllPlayers>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMySquad(squadEntity: SquadEntity) : Completable

    @Update
    fun updateMySquad(squadEntity: SquadEntity) : Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlayerSquad(playerSquadEntity: PlayerSquadEntity) : Completable

    @Query("SELECT * FROM squadplayers WHERE squad == :squadEntryId")
    fun getPlayersSquad(squadEntryId: String): LiveData<List<PlayerSquadEntity>>

}