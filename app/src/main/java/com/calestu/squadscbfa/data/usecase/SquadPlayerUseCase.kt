package com.calestu.squadscbfa.data.usecase

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.calestu.squadscbfa.data.executor.BaseSchedulerProvider
import com.calestu.squadscbfa.data.mapper.toEntity
import com.calestu.squadscbfa.data.mapper.toPlayerItemModelView
import com.calestu.squadscbfa.data.model.type.PlayerPositionFormationType
import com.calestu.squadscbfa.data.model.type.PlayerPositionType
import com.calestu.squadscbfa.data.repository.CoachRepository
import com.calestu.squadscbfa.data.repository.CurrentSquadRepository
import com.calestu.squadscbfa.data.repository.PlayerRepository
import com.calestu.squadscbfa.data.repository.SquadRepository
import com.calestu.squadscbfa.data.source.DataManager
import com.calestu.squadscbfa.ui.base.Resource
import com.calestu.squadscbfa.ui.module.player.model.PlayerItemModelView
import io.reactivex.Completable
import javax.inject.Inject

class SquadPlayerUseCase @Inject constructor(
    private val appDataManager: DataManager,
    private val playerRepository: PlayerRepository,
    private val squadRepository: SquadRepository,
    private val coachRepository: CoachRepository,
    private val currentSquadRepository: CurrentSquadRepository,
    private val schedulerProvider: BaseSchedulerProvider
) {

    private val result = MediatorLiveData<Resource<PagedList<PlayerItemModelView>>>()

    private fun asLiveData() = result as LiveData<Resource<PagedList<PlayerItemModelView>>>

    fun getPlayers(playerPositionType: PlayerPositionType): LiveData<Resource<PagedList<PlayerItemModelView>>> {

        setValue(Resource.loading(null))

        result.addSource(
            playerRepository.getPlayers(playerPositionType).toLiveData(pageSize = 20)
        )
        { newData ->
            setValue(Resource.success(newData))
        }

        return asLiveData()
    }

    fun insertPlayerSquad(
        squadEntryId: String,
        playerPositionFormationType: PlayerPositionFormationType,
        playerItemModelView: PlayerItemModelView): Completable =
        squadRepository.insertPlayerSquad(squadEntryId, playerPositionFormationType, playerItemModelView)
            .subscribeOn(schedulerProvider.diskIO())

    fun initLoading(): LiveData<Resource<PagedList<PlayerItemModelView>>> {

        setValue(Resource.loading(null))

        return asLiveData()
    }

    @MainThread
    private fun setValue(newValue: Resource<PagedList<PlayerItemModelView>>) {
        if (result.value != newValue) {
            result.value = newValue
        }
    }

}