package com.calestu.squadscbfa.data.usecase

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.calestu.squadscbfa.data.executor.BaseSchedulerProvider
import com.calestu.squadscbfa.data.model.type.PlayerPositionType
import com.calestu.squadscbfa.data.repository.CoachRepository
import com.calestu.squadscbfa.data.repository.CurrentSquadRepository
import com.calestu.squadscbfa.data.repository.PlayerRepository
import com.calestu.squadscbfa.data.source.DataManager
import com.calestu.squadscbfa.ui.base.Resource
import com.calestu.squadscbfa.ui.module.player.model.PlayerModelView
import timber.log.Timber
import javax.inject.Inject

class SquadPlayerUseCase @Inject constructor(
    private val appDataManager: DataManager,
    private val playerRepository: PlayerRepository,
    private val coachRepository: CoachRepository,
    private val currentSquadRepository: CurrentSquadRepository,
    private val schedulerProvider: BaseSchedulerProvider
) {

    private val result = MediatorLiveData<Resource<PagedList<PlayerModelView>>>()

    private fun asLiveData() = result as LiveData<Resource<PagedList<PlayerModelView>>>

    fun getPlayers(playerPositionType: PlayerPositionType): LiveData<Resource<PagedList<PlayerModelView>>> {

        result.addSource(
            playerRepository.getPlayers(playerPositionType).toLiveData(pageSize = 20)
        )
        { newData ->
            Timber.d("currentSquad: $newData")
            setValue(Resource.success(newData))
        }

        return asLiveData()
    }

    fun initLoading(): LiveData<Resource<PagedList<PlayerModelView>>> {

        setValue(Resource.loading(null))

        return asLiveData()
    }

    @MainThread
    private fun setValue(newValue: Resource<PagedList<PlayerModelView>>) {
        if (result.value != newValue) {
            result.value = newValue
        }
    }

}