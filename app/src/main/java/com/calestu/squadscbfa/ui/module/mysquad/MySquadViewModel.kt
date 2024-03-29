package com.calestu.squadscbfa.ui.module.mysquad

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.PagedList
import com.calestu.squadscbfa.data.usecase.MySquadUseCase
import com.calestu.squadscbfa.ui.base.Resource
import com.calestu.squadscbfa.ui.base.viewmodel.BaseViewModel
import com.calestu.squadscbfa.ui.module.mysquad.model.MySquadItemModelView
import com.calestu.squadscbfa.util.SingleLiveEvent
import timber.log.Timber
import javax.inject.Inject

class MySquadViewModel @Inject constructor(
    private val mySquadUseCase: MySquadUseCase
) : BaseViewModel(), MySquadUserActionsListener {

    val onClickedAddSquadEvent: SingleLiveEvent<Void> = SingleLiveEvent()
    val onClickedSquadEvent: SingleLiveEvent<String> = SingleLiveEvent()

    val emptySquads: LiveData<Boolean>

    val mySquadsLiveData: LiveData<Resource<PagedList<MySquadItemModelView>>> = mySquadUseCase.getMySquads()

    init {
        emptySquads = Transformations.map(mySquadsLiveData) {
            Timber.d("hasSquads:map ")
            it.data?.let { data ->
                data.isNullOrEmpty()
            } ?: true
        }
    }

    override fun clickedSquad(squad: MySquadItemModelView) {
        Timber.d("clickedSquad: $squad")
        onClickedSquadEvent.postValue(squad.entryid)
    }

    override fun clickedAddSquad() {
        Timber.d("clickedAddSquad: ")
        onClickedAddSquadEvent.call()
    }

    override fun onFirsTimeUiCreate(bundle: Bundle?) {
    }

}