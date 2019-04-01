package com.calestu.squadscbfa.ui.module.player

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.calestu.squadscbfa.data.mapper.getPlayerPositionType
import com.calestu.squadscbfa.data.model.type.PlayerPositionFormationType
import com.calestu.squadscbfa.data.model.type.PlayerPositionType
import com.calestu.squadscbfa.data.usecase.PlayerUseCase
import com.calestu.squadscbfa.ui.base.Resource
import com.calestu.squadscbfa.ui.base.viewmodel.BaseViewModel
import com.calestu.squadscbfa.ui.module.player.model.PlayerItemModelView
import com.calestu.squadscbfa.ui.module.player.model.PlayerItemModelViewStatus
import io.reactivex.rxkotlin.addTo
import timber.log.Timber
import javax.inject.Inject

class PlayerViewModel @Inject constructor(
    private val playerUseCase: PlayerUseCase
) : BaseViewModel() {

    private lateinit var playerArgs: PlayerArgs

    private val _players: MutableLiveData<Resource<List<PlayerItemModelView>>> by lazy {
        MutableLiveData<Resource<List<PlayerItemModelView>>>().apply {
            value = Resource.loading(null)
        }
    }

    val players: LiveData<Resource<List<PlayerItemModelView>>> = _players

    private fun loadPlayersByPosition() {
        Timber.d("loadPlayersByPosition")
        playerUseCase.getPlayersByPosition(playerArgs.playerPositionType)
            .subscribe(
                {
                    Timber.d("loadPlayersByPosition: $it")
                    setPlayersValue(Resource.success(it))

                },
                {
                    Timber.e(it, "loadPlayersByPosition.ERROR")
                    setPlayersValue(Resource.error(it.localizedMessage, null))
                }
            )
            .addTo(compositeDisposable)
    }

    private fun setPlayersValue(newValue: Resource<List<PlayerItemModelView>>) {
        if (_players.value != newValue) {
            _players.value = newValue
        }
    }

    private fun insertPlayerSquad(
        squadEntryId: String,
        playerPositionFormationType: PlayerPositionFormationType,
        playersWithSquadModel: PlayerItemModelView
    ) {
//        playerUseCase.insertPlayerSquad(squadEntryId, playerPositionFormationType, playersWithSquadModel)
//            .subscribe()
//            .addTo(compositeDisposable)

    }

    private fun deletePlayerSquad(
        player: PlayerItemModelView
    ) {
//        playerUseCase.deletePlayerSquad(player)
//            .subscribe()
//            .addTo(compositeDisposable)
    }

    fun clickedPlayer(player: PlayerItemModelView) {
        Timber.d("clickedPlayer: $player")

        player?.let {
            when {
                it.status == PlayerItemModelViewStatus.NOT_SELECTED -> {
                    Timber.d("clickedPlayer: NOT_SELECTED")
                    val list = _players.value!!.data!!.toMutableList()
                    list.find { cur -> cur.entryid == player.entryid }?.status = PlayerItemModelViewStatus.SELECTED

                    setPlayersValue(Resource.success(list.toList()))
                }
                it.status == PlayerItemModelViewStatus.SELECTED -> {
                    Timber.d("clickedPlayer: SELECTED")
                }
                else -> {
                    Timber.d("clickedPlayer: DISABLE")
                }
            }
        }

    }

    private fun setPlayerArgs(currentSquadEntryid: String, playerPositionFormationIndex: Int) {
        playerArgs = PlayerArgs(currentSquadEntryid, playerPositionFormationIndex)
        Timber.d("setPlayerArgs: $playerArgs")
    }

    data class PlayerArgs(
        val currentSquadEntryid: String,
        val playerPositionFormationIndex: Int) {

        val playerPositionFormationType:PlayerPositionFormationType
            get() = PlayerPositionFormationType.getPlayerPositionFormationType(playerPositionFormationIndex)

        val playerPositionType:PlayerPositionType
            get() = playerPositionFormationType.getPlayerPositionType()
    }

    override fun onFirsTimeUiCreate(bundle: Bundle?) {
        bundle?.let {
            val params = PlayerFragmentArgs.fromBundle(it)
            Timber.d("onFirsTimeUiCreate.currentSquadEntryid: ${params.currentSquadEntryid}")
            Timber.d("onFirsTimeUiCreate.playerPositionFormationIndex: ${params.playerPositionFormationIndex}")
            setPlayerArgs(params.currentSquadEntryid, params.playerPositionFormationIndex)
            loadPlayersByPosition()
        }
    }

}