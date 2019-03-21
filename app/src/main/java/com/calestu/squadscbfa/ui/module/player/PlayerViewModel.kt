package com.calestu.squadscbfa.ui.module.player

import android.os.Bundle
import com.calestu.squadscbfa.ui.base.viewmodel.BaseViewModel
import timber.log.Timber
import javax.inject.Inject

class PlayerViewModel @Inject constructor(
) : BaseViewModel() {

    init {
        Timber.d("init")
    }

    override fun onFirsTimeUiCreate(bundle: Bundle?) {
    }

}