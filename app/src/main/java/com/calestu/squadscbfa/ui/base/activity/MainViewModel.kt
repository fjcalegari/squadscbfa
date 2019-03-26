package com.calestu.squadscbfa.ui.base.activity

import android.os.Bundle
import androidx.lifecycle.LiveData
import com.calestu.squadscbfa.data.usecase.SyncUseCase
import com.calestu.squadscbfa.ui.base.Resource
import com.calestu.squadscbfa.ui.base.viewmodel.BaseViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor(
    syncUseCase: SyncUseCase
) : BaseViewModel() {

    val viewStateLiveData: LiveData<Resource<Boolean>> = syncUseCase.sync()

    override fun onFirsTimeUiCreate(bundle: Bundle?) {
    }

}