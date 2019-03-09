package com.calestu.squadscbfa.ui.module.main

import android.os.Bundle
import com.calestu.squadscbfa.ui.base.viewmodel.BaseViewModel
import timber.log.Timber
import javax.inject.Inject

class MainViewModel @Inject constructor(

) : BaseViewModel() {

    init {
        Timber.d("init")
    }

    override fun onFirsTimeUiCreate(bundle: Bundle?) {
        Timber.d("onFirsTimeUiCreate")
    }

}