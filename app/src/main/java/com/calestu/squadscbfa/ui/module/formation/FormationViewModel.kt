package com.calestu.squadscbfa.ui.module.formation

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.calestu.squadscbfa.data.entity.CurrentSquadEntity
import com.calestu.squadscbfa.data.model.type.FormationType
import com.calestu.squadscbfa.data.repository.CurrentSquadRepository
import com.calestu.squadscbfa.ui.base.viewmodel.BaseViewModel
import com.calestu.squadscbfa.util.SingleLiveEvent
import io.reactivex.Single
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class FormationViewModel @Inject constructor(
    private val currentSquadRepository: CurrentSquadRepository
) : BaseViewModel() {

    val selectedFormationConfirmed: SingleLiveEvent<Boolean> = SingleLiveEvent()

    private val _formations = MutableLiveData<List<FormationType>>()
    val formations: LiveData<List<FormationType>> = _formations

    private var currentFormationFlowType = FormationFlowType.NONE

    private fun setListFormations(list: List<FormationType>) {
        _formations.value = list
    }

    fun formationSelected(formationType: FormationType) {
        Timber.d("formationSelected: $formationType")

        currentSquadRepository.getLocalCurrentSquad().
            map { t ->  t.copy(formation = formationType.index)}
            .flatMap {
                currentSquadRepository.updateCurrentSquad(it).subscribeOn(Schedulers.io()).subscribe().addTo(compositeDisposable)
                Single.just(it)
            }
            .subscribeOn(Schedulers.io())
            .subscribe()
            .addTo(compositeDisposable)

        selectedFormationConfirmed.postValue(true)
    }

    override fun onFirsTimeUiCreate(bundle: Bundle?) {
        bundle?.let {
            setListFormations(FormationType.getFormations(it.getInt(FormationFragment.KEY_INDEX_SELECTED, 0)))
            currentFormationFlowType =
                    when(it.getInt(FormationFragment.KEY_FLOW_TYPE, 0)) {
                        FormationFlowType.SQUAD_ADD.ordinal -> FormationFlowType.SQUAD_ADD
                        else -> FormationFlowType.NONE
                    }
        }
    }

}