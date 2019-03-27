package com.calestu.squadscbfa.data.usecase

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.calestu.squadscbfa.data.entity.SquadEntity
import com.calestu.squadscbfa.data.executor.BaseSchedulerProvider
import com.calestu.squadscbfa.data.repository.CoachRepository
import com.calestu.squadscbfa.data.repository.CurrentSquadRepository
import com.calestu.squadscbfa.data.repository.PlayerRepository
import com.calestu.squadscbfa.data.repository.SquadRepository
import com.calestu.squadscbfa.data.source.DataManager
import com.calestu.squadscbfa.ui.base.Resource
import com.calestu.squadscbfa.ui.module.squad.edit.model.SquadEditModelView
import com.calestu.squadscbfa.util.ext.dateTimeNow
import io.reactivex.Completable
import timber.log.Timber
import java.util.*
import javax.inject.Inject

class SquadEditUseCase @Inject constructor(
    private val appDataManager: DataManager,
    private val squadRepository: SquadRepository,
    private val playerRepository: PlayerRepository,
    private val coachRepository: CoachRepository,
    private val currentSquadRepository: CurrentSquadRepository,
    private val schedulerProvider: BaseSchedulerProvider
) {

    private val result = MediatorLiveData<Resource<SquadEditModelView>>()

    private fun asLiveData() = result as LiveData<Resource<SquadEditModelView>>

    fun getMySquad(entryid: String): LiveData<Resource<SquadEditModelView>> {
        Timber.d("getMySquad: $entryid")

        setValue(Resource.loading(null))

        result.addSource(
            squadRepository.getMySquad(entryid)
        )
        { newData ->
            Timber.d("getMySquad: $newData")
            setValue(Resource.success(newData))
        }

        return asLiveData()
    }

    fun createMySquad(squadEntryid: String) : Completable =
        squadRepository.insertMySquad(createDefaultSquad(squadEntryid))
            .subscribeOn(schedulerProvider.diskIO())
            .observeOn(schedulerProvider.ui())

    private fun createDefaultSquad(squadEntryid: String) : SquadEntity {
        val currentRoundType = appDataManager.getCurrentRound()
        val currentFormationType = appDataManager.getCurrentFormation()
        val userUid = appDataManager.getUserUid()
        return SquadEntity(
            entryid = squadEntryid,
            owner = userUid,
            createdTime = Date().dateTimeNow(),
            updatedTime = Date().dateTimeNow(),
            round = currentRoundType,
            title = "Escalação ${currentRoundType.title}",
            formation = currentFormationType
        )
    }

    fun initLoading(): LiveData<Resource<SquadEditModelView>> {
        Timber.d("initLoading: ")

        setValue(Resource.loading(null))

        return asLiveData()
    }

    @MainThread
    private fun setValue(newValue: Resource<SquadEditModelView>) {
        if (result.value != newValue) {
            result.value = newValue
        }
    }

}