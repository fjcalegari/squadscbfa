package com.calestu.squadscbfa.ui.module.player

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.PagedList
import com.calestu.squadscbfa.data.model.type.PlayerPositionFormationType
import com.calestu.squadscbfa.data.model.type.PlayerPositionType
import com.calestu.squadscbfa.data.usecase.SquadPlayerUseCase
import com.calestu.squadscbfa.ui.base.Resource
import com.calestu.squadscbfa.ui.base.viewmodel.BaseViewModel
import com.calestu.squadscbfa.ui.module.player.model.PlayerModelView
import com.calestu.squadscbfa.util.AbsentLiveData
import timber.log.Timber
import javax.inject.Inject

class PlayerViewModel @Inject constructor(
    private val playerUseCase: SquadPlayerUseCase
) : BaseViewModel() {

    private val _playersArgs = MutableLiveData<PlayerArgs>()

    val players: LiveData<Resource<PagedList<PlayerModelView>>> = Transformations
        .switchMap(_playersArgs) {args ->
            if (args == null) {
                playerUseCase.initLoading()
            } else {
                playerUseCase.getPlayers(args.playerPositionType)
            }
        }

    fun setPlayersArgs(currentSquadEntryid: String, playerPositionFormationIndex: Int) {
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

    fun clickedPlayer(player: PlayerModelView) {
        Timber.d("clickedPlayer: $player")
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