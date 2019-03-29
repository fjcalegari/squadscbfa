package com.calestu.squadscbfa.data.usecase

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.calestu.squadscbfa.data.executor.BaseSchedulerProvider
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
import timber.log.Timber
import javax.inject.Inject

class SquadPlayerUseCase @Inject constructor(
    private val appDataManager: DataManager,
    private val playerRepository: PlayerRepository,
    private val squadRepository: SquadRepository,
    private val coachRepository: CoachRepository,
    private val currentSquadRepository: CurrentSquadRepository,
    private val schedulerProvider: BaseSchedulerProvider
) {

    private val result = MediatorLiveData<Resource<List<PlayerItemModelView>>>()

    private fun asLiveData() = result as LiveData<Resource<List<PlayerItemModelView>>>

//    fun getPlayersAndInSquad(currentSquadEntryid: String, playerPositionType: PlayerPositionType) : LiveData<Resource<List<PlayerItemModelView>>> {
//        Timber.d("getPlayersAndInSquad: ")
////        setValue(Resource.loading(null))
//
//        val liveDataPlayers = playerRepository.getPlayers(playerPositionType)
//        val liveDataPlayersInSquad = squadRepository.getPlayersSquad(currentSquadEntryid, playerPositionType)
//
//        result.addSource(liveDataPlayers) { value ->
//            Timber.d("getPlayersAndInSquad: liveDataPlayers")
//            setValue(combineLatestData(liveDataPlayers, liveDataPlayersInSquad))
//        }
//        result.addSource(liveDataPlayersInSquad) { value ->
//            Timber.d("getPlayersAndInSquad: liveDataPlayersInSquad")
//            setValue(combineLatestData(liveDataPlayers, liveDataPlayersInSquad))
//        }
//        return asLiveData()
//    }
//
//    private fun combineLatestData(
//        liveDataPlayersResult: LiveData<List<PlayerItemModelView>>,
//        liveDataPlayersInSquadResult: LiveData<List<PlayerItemModelView>>
//    ): Resource<List<PlayerItemModelView>> {
//
//        val players = liveDataPlayersResult.value
//        val playersInSquad = liveDataPlayersInSquadResult.value
//
//        // Don't send a success until we have both results
//        if (players == null || playersInSquad == null) {
//            return Resource.loading(null)
//        }
//
//        val newData : MutableList<PlayerItemModelView> = mutableListOf()
//
//        players?.forEach { player ->
//
//            val plaInSquad = playersInSquad?.singleOrNull { playerItemModelView ->  playerItemModelView.entryid == player.entryid }
//
//            if (plaInSquad != null) {
//                newData.add(player.copy(inSquad = true))
//            } else {
//                newData.add(player.copy(inSquad = false))
//            }
//
//        }
//
//        return Resource.success(newData)
//    }

    fun getPlayers(currentSquadEntryid: String, playerPositionType: PlayerPositionType): LiveData<Resource<List<PlayerItemModelView>>> {

        setValue(Resource.loading(null))

        result.addSource(
            playerRepository.getPlayers(playerPositionType)
        )
        { newData ->
            setValue(Resource.success(newData))
        }

        return asLiveData()
    }

//    fun getPlayersSquad(currentSquadEntryid: String, playerPositionType: PlayerPositionType): LiveData<List<PlayerItemModelView>> {
////        result.addSource(
////
////        )
////        { newData ->
////            Timber.d("#################################################################################")
////            Timber.d("getPlayersSquad: $newData")
////            Timber.d("#################################################################################")
////            setValue(Resource.success(newData))
////        }
//
//        return squadRepository.getPlayersSquad(currentSquadEntryid, playerPositionType)
//    }

    fun insertPlayerSquad(
        squadEntryId: String,
        playerPositionFormationType: PlayerPositionFormationType,
        playerItemModelView: PlayerItemModelView): Completable =
        squadRepository.insertPlayerSquad(squadEntryId, playerPositionFormationType, playerItemModelView)
            .subscribeOn(schedulerProvider.diskIO())

    fun initLoading(): LiveData<Resource<List<PlayerItemModelView>>> {

        setValue(Resource.loading(null))

        return asLiveData()
    }

    @MainThread
    private fun setValue(newValue: Resource<List<PlayerItemModelView>>) {
        if (result.value != newValue) {
            result.value = newValue
        }
    }

}