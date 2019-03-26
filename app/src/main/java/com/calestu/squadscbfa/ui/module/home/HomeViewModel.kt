package com.calestu.squadscbfa.ui.module.home

import android.os.Bundle
import com.calestu.squadscbfa.ui.base.viewmodel.BaseViewModel
import com.calestu.squadscbfa.util.SingleLiveEvent
import timber.log.Timber
import javax.inject.Inject

class HomeViewModel @Inject constructor(

) : BaseViewModel(), HomeUserActionsListener {

    val onClickedMySquadsEvent: SingleLiveEvent<Void> = SingleLiveEvent()

    override fun clickedMySquads() {
        Timber.d("clickedMySquads: ")
        onClickedMySquadsEvent.call()
    }

    override fun onFirsTimeUiCreate(bundle: Bundle?) {
        Timber.d("onFirsTimeUiCreate: ")
    }

}