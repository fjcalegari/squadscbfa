package com.calestu.squadscbfa.ui.module.squad.add

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
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
) : BaseViewModel(), SquadAddUserActionsListener, SquadPlayerUserActionsListener {

    val onClickedFormationEvent: SingleLiveEvent<Int> = SingleLiveEvent()

    val currentSquadLiveData: LiveData<Resource<SquadAddModel>> = squadAddUseCase.currentSquad()

    init {
        Timber.d("init")
    }

    override fun clickedFormationButton(indexSelected: Int) {
        onClickedFormationEvent.postValue(indexSelected)
    }

    override fun clickedPlayer(player: PlayerSquadModel) {
        Timber.d("clickedPlayer: $player")
    }

    override fun onFirsTimeUiCreate(bundle: Bundle?) {
    }

}