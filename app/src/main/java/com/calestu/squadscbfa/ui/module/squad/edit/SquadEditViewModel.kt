package com.calestu.squadscbfa.ui.module.squad.edit

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.calestu.squadscbfa.data.usecase.SquadEditUseCase
import com.calestu.squadscbfa.ui.base.Resource
import com.calestu.squadscbfa.ui.base.viewmodel.BaseViewModel
import com.calestu.squadscbfa.ui.module.squad.edit.model.SquadCoachEditModelView
import com.calestu.squadscbfa.ui.module.squad.edit.model.SquadEditModelView
import com.calestu.squadscbfa.ui.module.squad.edit.model.SquadPlayerPositionEditModelView
import com.calestu.squadscbfa.util.SingleLiveEvent
import io.reactivex.rxkotlin.addTo
import timber.log.Timber
import javax.inject.Inject

class SquadEditViewModel @Inject constructor(

    private val squadEditUseCase: SquadEditUseCase

) : BaseViewModel(), SquadEditUserActionsListener, SquadEditPlayerUserActionsListener, SquadEditCoachUserActionsListener {

    val onClickedFormationEvent: SingleLiveEvent<Int> = SingleLiveEvent()
    val onClickedAddPlayerEvent: SingleLiveEvent<Pair<String, Int>> = SingleLiveEvent()

    private val _squad: MutableLiveData<Resource<SquadEditModelView>> by lazy {
        MutableLiveData<Resource<SquadEditModelView>>().apply {
            value = Resource.loading(null)
        }
    }

    val squad: LiveData<Resource<SquadEditModelView>> = _squad

    private fun createMySquad() {
        Timber.d("createMySquad")
        squadEditUseCase.createMySquad()
            .subscribe(
                {
                    Timber.d("createMySquad.SUCCESS")
                },
                {
                    Timber.e(it, "createMySquadERROR")
                    setSquadValue(Resource.error(it.localizedMessage, null))
                }
            )
            .addTo(compositeDisposable)
    }

    private fun loadMySquad(entryid: String) {
        Timber.d("loadMySquad: $entryid")
        squadEditUseCase.loadMySquad(entryid)
            .subscribe(
                {
                    Timber.d("loadMySquad: $it")
                    setSquadValue(Resource.success(it))

                },
                {
                    Timber.e(it, "loadMySquad.ERROR")
                    setSquadValue(Resource.error(it.localizedMessage, null))
                }
            )
            .addTo(compositeDisposable)
    }

    override fun clickedPlayer(player: SquadPlayerPositionEditModelView) {
        Timber.d("clickedPlayer: $player")
        squad.value?.let {livedata ->
            livedata.data?.let {
                Timber.d("clickedPlayer.SquadAddModel: $it")
                onClickedAddPlayerEvent.postValue(Pair(it.entryid, player.positionFormation.index))
            }
        }
    }

    override fun clickedCoach(coach: SquadCoachEditModelView) {
        Timber.d("clickedCoach: $coach")
        squad.value?.let {livedata ->
            livedata.data?.let {
                Timber.d("clickedCoach.SquadAddModel: $it")
            }
        }
    }

    private fun setSquadValue(newValue: Resource<SquadEditModelView>) {
        if (_squad.value != newValue) {
            _squad.value = newValue
        }
    }

    override fun clickedFormationButton(indexSelected: Int) {
        onClickedFormationEvent.postValue(indexSelected)
    }

    override fun onFirsTimeUiCreate(bundle: Bundle?) {
        Timber.d("onFirsTimeUiCreate: ")
        bundle?.let {
            val params = SquadEditFragmentArgs.fromBundle(it)
            Timber.d("onFirsTimeUiCreate.squadEntryid: ${params.squadEntryid}")
            if (params.squadEntryid.isNullOrEmpty()) {
                Timber.d("onFirsTimeUiCreate: CREATE")
                createMySquad()

            } else {
                Timber.d("onFirsTimeUiCreate: READ")
                loadMySquad(params.squadEntryid)
            }
        }
    }

}