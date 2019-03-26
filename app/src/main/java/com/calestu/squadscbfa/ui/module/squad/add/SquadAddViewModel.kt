package com.calestu.squadscbfa.ui.module.squad.add

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.calestu.squadscbfa.data.model.CoachSquadModel
import com.calestu.squadscbfa.data.model.PlayerSquadModel
import com.calestu.squadscbfa.data.usecase.SquadAddUseCase
import com.calestu.squadscbfa.ui.base.Resource
import com.calestu.squadscbfa.ui.base.viewmodel.BaseViewModel
import com.calestu.squadscbfa.ui.module.squad.add.model.SquadAddModel
import com.calestu.squadscbfa.util.SingleLiveEvent
import timber.log.Timber
import javax.inject.Inject

class SquadAddViewModel @Inject constructor(
    private val squadAddUseCase: SquadAddUseCase
) : BaseViewModel(),
    SquadAddUserActionsListener, SquadPlayerUserActionsListener, SquadCoachUserActionsListener {

    val onClickedFormationEvent: SingleLiveEvent<Int> = SingleLiveEvent()

    val onClickedAddPlayerEvent: SingleLiveEvent<Pair<String, Int>> = SingleLiveEvent()

    val currentSquadLiveData: LiveData<Resource<SquadAddModel>> = squadAddUseCase.currentSquad()

    init {
        Timber.d("init")
    }

    override fun clickedFormationButton(indexSelected: Int) {
        onClickedFormationEvent.postValue(indexSelected)
    }

    override fun clickedPlayer(player: PlayerSquadModel) {
        Timber.d("clickedPlayer: $player")
        currentSquadLiveData.value?.let {livedata ->
            livedata.data?.let {
                Timber.d("clickedPlayer.SquadAddModel: $it")
                onClickedAddPlayerEvent.postValue(Pair(it.entryid, player.playerPositionFormationType.index))
            }
        }
    }

    override fun clickedCoach(coach: CoachSquadModel) {
        Timber.d("clickedCoach: $coach")
        currentSquadLiveData.value?.let {livedata ->
            livedata.data?.let {
                Timber.d("clickedCoach.SquadAddModel: $it")
            }
        }
    }

    override fun onFirsTimeUiCreate(bundle: Bundle?) {
    }

}