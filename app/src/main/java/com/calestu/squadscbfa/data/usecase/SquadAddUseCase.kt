package com.calestu.squadscbfa.data.usecase

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.calestu.squadscbfa.data.entity.CurrentSquadEntity
import com.calestu.squadscbfa.data.executor.BaseSchedulerProvider
import com.calestu.squadscbfa.data.mapper.fromEntity
import com.calestu.squadscbfa.data.model.type.FormationType
import com.calestu.squadscbfa.data.model.type.RoundType
import com.calestu.squadscbfa.data.repository.CoachRepository
import com.calestu.squadscbfa.data.repository.CurrentSquadRepository
import com.calestu.squadscbfa.data.repository.PlayerRepository
import com.calestu.squadscbfa.data.source.AppDataManager
import com.calestu.squadscbfa.data.source.DataManager
import com.calestu.squadscbfa.ui.base.Resource
import com.calestu.squadscbfa.ui.module.squad.add.model.SquadAddModel
import com.calestu.squadscbfa.util.ext.toLiveData
import com.calestu.squadscbfa.util.ext.toState
import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.SingleTransformer
import io.reactivex.rxkotlin.addTo
import timber.log.Timber
import javax.inject.Inject

class SquadAddUseCase @Inject constructor(
    private val appDataManager: DataManager,
    private val playerRepository: PlayerRepository,
    private val coachRepository: CoachRepository,
    private val currentSquadRepository: CurrentSquadRepository,
    private val schedulerProvider: BaseSchedulerProvider
) {

    private val result = MediatorLiveData<Resource<SquadAddModel>>()

    private fun asLiveData() = result as LiveData<Resource<SquadAddModel>>

    fun currentSquad(): LiveData<Resource<SquadAddModel>> {
        existCurrentSquad()

        setValue(Resource.loading(null))

        result.addSource(
            currentSquadRepository.currentSquad()
                .subscribeOn(schedulerProvider.io())
                .map {
                    it.fromEntity()
                }
                .observeOn(schedulerProvider.ui())
                .toState()
                .toLiveData()
        ) { newData ->
            Timber.d("currentSquad: $newData")
            setValue(newData)
        }

        return asLiveData()
    }

    private fun existCurrentSquad() {
        Timber.d("existCurrentSquad: ")

        currentSquadRepository.getLocalCountCurrentSquad()
            .compose(currentSquadTransformer())
            .subscribeOn(schedulerProvider.diskIO())
            .subscribe()
    }

    private fun currentSquadTransformer() = object : SingleTransformer<Int, Boolean> {
        override fun apply(upstream: Single<Int>): SingleSource<Boolean> {
            return upstream.flatMap {
                if (it == 0) {
                    currentSquadRepository.insertCurrentSquad(createDefaultCurrentSquad()).toSingleDefault(true)
                } else {
                    Single.just(true)
                }
            }
        }
    }

    fun createDefaultCurrentSquad() : CurrentSquadEntity {
        val currentRoundType = appDataManager.getCurrentRound()
        val currentFormationType = appDataManager.getCurrentFormation()
        return CurrentSquadEntity(
            round = currentRoundType,
            title = "Escalação ${currentRoundType.title}",
            formation = currentFormationType
        )
    }

    @MainThread
    private fun setValue(newValue: Resource<SquadAddModel>) {
        if (result.value != newValue) {
            result.value = newValue
        }
    }

}