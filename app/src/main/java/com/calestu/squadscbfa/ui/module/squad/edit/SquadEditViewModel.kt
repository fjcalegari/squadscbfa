package com.calestu.squadscbfa.ui.module.squad.edit

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.calestu.squadscbfa.data.model.CoachSquadModel
import com.calestu.squadscbfa.data.model.PlayerSquadModel
import com.calestu.squadscbfa.data.usecase.SquadEditUseCase
import com.calestu.squadscbfa.ui.base.Resource
import com.calestu.squadscbfa.ui.base.viewmodel.BaseViewModel
import com.calestu.squadscbfa.ui.module.squad.edit.model.SquadEditModelView
import com.calestu.squadscbfa.util.SingleLiveEvent
import io.reactivex.rxkotlin.addTo
import timber.log.Timber
import java.util.*
import javax.inject.Inject

class SquadEditViewModel @Inject constructor(

    private val squadEditUseCase: SquadEditUseCase

) : BaseViewModel(), SquadEditUserActionsListener, SquadEditPlayerUserActionsListener, SquadEditCoachUserActionsListener {

    val onClickedFormationEvent: SingleLiveEvent<Int> = SingleLiveEvent()
    val onClickedAddPlayerEvent: SingleLiveEvent<Pair<String, Int>> = SingleLiveEvent()

    private val _squadEntryidArgs = MutableLiveData<String>()

    val squad: LiveData<Resource<SquadEditModelView>> = Transformations
        .switchMap(_squadEntryidArgs) {args ->
            if (args.isNullOrEmpty()) {
                squadEditUseCase.initLoading()
            } else {
                squadEditUseCase.getMySquad(args)
            }
        }

    private fun createMySquad(squadEntryid: String) {
        Timber.d("createMySquad: $squadEntryid")
        squadEditUseCase.createMySquad(squadEntryid)
            .subscribe(
                {
                    Timber.d("createMySquad.SUCCESS")
                    _squadEntryidArgs.value = squadEntryid
                },
                {
                    Timber.e(it, "createMySquadERROR")
                }
            )
            .addTo(compositeDisposable)
    }

    override fun clickedPlayer(player: PlayerSquadModel) {
        Timber.d("clickedPlayer: $player")
        squad.value?.let {livedata ->
            livedata.data?.let {
                Timber.d("clickedPlayer.SquadAddModel: $it")
                onClickedAddPlayerEvent.postValue(Pair(it.entryid, player.playerPositionFormationType.index))
            }
        }
    }

    override fun clickedCoach(coach: CoachSquadModel) {
        Timber.d("clickedCoach: $coach")
        squad.value?.let {livedata ->
            livedata.data?.let {
                Timber.d("clickedCoach.SquadAddModel: $it")
            }
        }
    }

    override fun clickedFormationButton(indexSelected: Int) {
        onClickedFormationEvent.postValue(indexSelected)
    }

    override fun onFirsTimeUiCreate(bundle: Bundle?) {
        _squadEntryidArgs.value = ""
        Timber.d("onFirsTimeUiCreate: ")
        bundle?.let {
            val params = SquadEditFragmentArgs.fromBundle(it)
            Timber.d("onFirsTimeUiCreate.squadEntryid: ${params.squadEntryid}")
            if (params.squadEntryid.isNullOrEmpty()) {
                Timber.d("onFirsTimeUiCreate: CREATE")
                createMySquad(UUID.randomUUID().toString())
            } else {
                Timber.d("onFirsTimeUiCreate: READ")
                _squadEntryidArgs.value = params.squadEntryid
            }
        }
    }

}