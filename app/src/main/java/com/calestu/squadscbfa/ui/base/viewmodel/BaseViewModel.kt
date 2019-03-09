package com.calestu.squadscbfa.ui.base.viewmodel

import androidx.lifecycle.ViewModel
import android.os.Bundle
import androidx.annotation.StringRes
import com.calestu.squadscbfa.util.SingleLiveEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

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
        compositeDisposable.dispose()
    }

    protected abstract fun onFirsTimeUiCreate(bundle:Bundle?)

    fun showSnackbarMessage(@StringRes message: Int) {
        snackbarMessage.value = message
    }

    fun addDisposable(disposable: Disposable) = compositeDisposable.add(disposable)

}