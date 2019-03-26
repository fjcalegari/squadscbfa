package com.calestu.squadscbfa.data.usecase

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.calestu.squadscbfa.data.executor.BaseSchedulerProvider
import com.calestu.squadscbfa.data.repository.CoachRepository
import com.calestu.squadscbfa.data.repository.CurrentSquadRepository
import com.calestu.squadscbfa.data.repository.PlayerRepository
import com.calestu.squadscbfa.data.repository.SquadRepository
import com.calestu.squadscbfa.data.source.DataManager
import com.calestu.squadscbfa.ui.base.Resource
import com.calestu.squadscbfa.ui.module.mysquad.model.MySquadItemModelView
import timber.log.Timber
import javax.inject.Inject

class MySquadUseCase @Inject constructor(
    private val appDataManager: DataManager,
    private val squadRepository: SquadRepository,
    private val playerRepository: PlayerRepository,
    private val coachRepository: CoachRepository,
    private val currentSquadRepository: CurrentSquadRepository,
    private val schedulerProvider: BaseSchedulerProvider
) {

    private val result = MediatorLiveData<Resource<PagedList<MySquadItemModelView>>>()

    private fun asLiveData() = result as LiveData<Resource<PagedList<MySquadItemModelView>>>

    fun getMySquads(): LiveData<Resource<PagedList<MySquadItemModelView>>> {

        setValue(Resource.loading(null))

        result.addSource(
            squadRepository.getMySquads(appDataManager.getUserUid()).toLiveData(pageSize = 20)
        )
        { newData ->
            Timber.d("getMySquads: $newData")
            setValue(Resource.success(newData))
        }

        return asLiveData()
    }

    @MainThread
    private fun setValue(newValue: Resource<PagedList<MySquadItemModelView>>) {
        if (result.value != newValue) {
            result.value = newValue
        }
    }

}