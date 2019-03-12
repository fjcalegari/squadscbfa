package com.calestu.squadscbfa.ui.base.activity

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.calestu.squadscbfa.data.usecase.SyncUseCase
import com.calestu.squadscbfa.ui.base.Resource
import com.calestu.squadscbfa.ui.base.viewmodel.BaseViewModel
import com.calestu.squadscbfa.util.ext.toState
import io.reactivex.rxkotlin.addTo
import timber.log.Timber
import javax.inject.Inject

class MainViewModel @Inject constructor(
    syncUseCase: SyncUseCase

) : BaseViewModel() {

    private val _viewStateLiveData: MutableLiveData<Resource<Boolean>> by lazy {
        MutableLiveData<Resource<Boolean>>()
            .apply { value = Resource.loading() }
    }

    var viewStateLiveData: LiveData<Resource<Boolean>> = _viewStateLiveData

    init {
        Timber.d("init")

        syncUseCase.syncApp()
            .toState()
            .subscribe(this::setViewState)
            .addTo(compositeDisposable)
    }

    private fun setViewState(res : Resource<Boolean>?, throwable: Throwable?) {
        res?.let {
            _viewStateLiveData.value = it
        }
        throwable?.let {
            _viewStateLiveData.value = Resource.error(it.localizedMessage, null)
        }

    }

    override fun onFirsTimeUiCreate(bundle: Bundle?) {
        Timber.d("onFirsTimeUiCreate")
    }

}