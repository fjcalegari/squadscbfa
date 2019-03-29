package com.calestu.squadscbfa.ui.module.player

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.calestu.squadscbfa.data.model.type.PlayerPositionFormationType
import com.calestu.squadscbfa.data.model.type.PlayerPositionType
import com.calestu.squadscbfa.data.usecase.SquadPlayerUseCase
import com.calestu.squadscbfa.ui.base.Resource
import com.calestu.squadscbfa.ui.base.viewmodel.BaseViewModel
import com.calestu.squadscbfa.ui.module.player.model.PlayerItemModelView
import com.calestu.squadscbfa.util.AbsentLiveData
import io.reactivex.rxkotlin.addTo
import timber.log.Timber
import javax.inject.Inject

class PlayerViewModel @Inject constructor(
    private val playerUseCase: SquadPlayerUseCase
) : BaseViewModel() {

    private val _playersArgs = MutableLiveData<PlayerArgs>()
    private val _aaaarrgg = MutableLiveData<PlayerArgs>()

    val players: LiveData<Resource<List<PlayerItemModelView>>> = Transformations
        .switchMap(_playersArgs) {args ->
            if (args == null) {
                playerUseCase.initLoading()
            } else {
                playerUseCase.getPlayers(args.currentSquadEntryid, args.playerPositionType)
            }
        }

//    val playersInSquad: LiveData<List<PlayerItemModelView>> =
//        Transformations
//        .switchMap(_aaaarrgg) {args ->
//            if (args == null) {
//                AbsentLiveData.create()
//            } else {
//                playerUseCase.getPlayersSquad(args.currentSquadEntryid, args.playerPositionType)
//            }
//        }

    private fun insertPlayerSquad(
        squadEntryId: String,
        playerPositionFormationType: PlayerPositionFormationType,
        playerItemModelView: PlayerItemModelView) {

        playerUseCase.insertPlayerSquad(squadEntryId, playerPositionFormationType, playerItemModelView)
            .subscribe()
            .addTo(compositeDisposable)

    }

    fun clickedPlayer(player: PlayerItemModelView) {
        Timber.d("clickedPlayer: $player")
        Timber.d("clickedPlayer_playersArgs: ${_playersArgs.value}")
        _playersArgs.value?.let {
            insertPlayerSquad(it.currentSquadEntryid, it.playerPositionFormationType, player)
        }
    }

    private fun setPlayersArgs(currentSquadEntryid: String, playerPositionFormationIndex: Int) {
        val playersArgs = PlayerArgs(currentSquadEntryid, playerPositionFormationIndex)
        Timber.d("setPlayersArgs: $playersArgs")
        if (_playersArgs.value == playersArgs) {
            return
        }
        _playersArgs.value = playersArgs
    }

    data class PlayerArgs(
        val currentSquadEntryid: String,
        val playerPositionFormationIndex: Int) {

        val playerPositionFormationType:PlayerPositionFormationType
            get() = PlayerPositionFormationType.getPlayerPositionFormationType(playerPositionFormationIndex)

        val playerPositionType:PlayerPositionType
            get() = playerPositionFormationType.playerPositionType
    }

    override fun onFirsTimeUiCreate(bundle: Bundle?) {
        bundle?.let {
            val params = PlayerFragmentArgs.fromBundle(it)
            Timber.d("onFirsTimeUiCreate.currentSquadEntryid: ${params.currentSquadEntryid}")
            Timber.d("onFirsTimeUiCreate.playerPositionFormationIndex: ${params.playerPositionFormationIndex}")
            setPlayersArgs(params.currentSquadEntryid, params.playerPositionFormationIndex)
        }
    }

}