package com.calestu.squadscbfa.data.usecase

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.calestu.squadscbfa.data.executor.BaseSchedulerProvider
import com.calestu.squadscbfa.data.mapper.fromEntity
import com.calestu.squadscbfa.data.repository.ClubRepository
import com.calestu.squadscbfa.data.repository.CoachRepository
import com.calestu.squadscbfa.data.repository.CurrentSquadRepository
import com.calestu.squadscbfa.data.repository.PlayerRepository
import com.calestu.squadscbfa.ui.base.Resource
import com.calestu.squadscbfa.ui.module.squad.add.model.SquadAddModel
import javax.inject.Inject

class SquadAddUseCase @Inject constructor(
    private val clubRepository: ClubRepository,
    private val playerRepository: PlayerRepository,
    private val coachRepository: CoachRepository,
    private val currentSquadRepository: CurrentSquadRepository,
    private val schedulerProvider: BaseSchedulerProvider
) {

    private val result = MediatorLiveData<Resource<SquadAddModel>>()

    private fun asLiveData() = result as LiveData<Resource<SquadAddModel>>

    fun currentSquad(): LiveData<Resource<SquadAddModel>> {
        setValue(Resource.loading(null))

        result.addSource(currentSquadRepository.getCurrentSquad()) { newData ->
            setValue(Resource.success(newData.fromEntity()))
        }

        return asLiveData()
    }
//        .getCurrentSquad()
//        .toFlowable()
//        .toState()
//        .compose(flowableForUI(schedulerProvider))
//        .map { t ->  t.data!!}
//        .toLiveData()

    @MainThread
    private fun setValue(newValue: Resource<SquadAddModel>) {
        if (result.value != newValue) {
            result.value = newValue
        }
    }

}