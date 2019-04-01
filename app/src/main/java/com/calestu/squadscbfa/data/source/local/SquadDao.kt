package com.calestu.squadscbfa.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.calestu.squadscbfa.data.entity.PlayerEntity
import com.calestu.squadscbfa.data.entity.PlayerSquadEntity
import com.calestu.squadscbfa.data.entity.SquadEntity
import com.calestu.squadscbfa.data.model.SquadWithPlayersDbModel
import com.calestu.squadscbfa.data.model.type.PlayerPositionType
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface SquadDao {

    @Query("SELECT * FROM squad WHERE owner == :ownerUid ORDER BY updatedTime DESC")
    fun getMySquads(ownerUid: String): DataSource.Factory<Int, SquadEntity>

    @Transaction
    @Query("SELECT * FROM squad WHERE entryid == :entryid")
    fun getMySquad(entryid: String): Single<SquadWithPlayersDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMySquad(squadEntity: SquadEntity) : Long

    @Update
    fun updateMySquad(squadEntity: SquadEntity) : Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlayerSquad(playerSquadEntity: PlayerSquadEntity) : Completable

    @Update
    fun updatePlayerSquad(playerSquadEntity: PlayerSquadEntity) : Completable

    @Delete
    fun deletePlayerSquad(playerSquadEntity: PlayerSquadEntity) : Completable

    @Query("SELECT * FROM player WHERE pos == :playerPositionType ORDER BY name ASC")
    fun getPlayersByPosition(playerPositionType: PlayerPositionType): Single<List<PlayerEntity>>

}