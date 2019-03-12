package com.calestu.squadscbfa.ui.base.viewmodel

import androidx.lifecycle.ViewModel
import android.os.Bundle
import androidx.annotation.StringRes
import com.calestu.squadscbfa.util.SingleLiveEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import timber.log.Timber

abstract class BaseViewModel : ViewModel() {

    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    val snackbarMessage = SingleLiveEvent<Int>()

    private var isFirstTimeUiCreate = true

    fun onCreate(bundle: Bundle?) {
        if (isFirstTimeUiCreate) {
            onFirsTimeUiCreate(bundle)
            isFirstTimeUiCreate = false
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
        Timber.d("onCleared: ")
    }

    protected abstract fun onFirsTimeUiCreate(bundle:Bundle?)

    fun showSnackbarMessage(@StringRes message: Int) {
        snackbarMessage.value = message
    }

}