package com.calestu.squadscbfa.ui.base.activity

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.calestu.squadscbfa.data.entity.CurrentSquadEntity
import com.calestu.squadscbfa.data.repository.CurrentSquadRepository
import com.calestu.squadscbfa.data.usecase.SyncUseCase
import com.calestu.squadscbfa.ui.base.Resource
import com.calestu.squadscbfa.ui.base.viewmodel.BaseViewModel
import com.calestu.squadscbfa.util.ext.toState
import io.reactivex.Single
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val syncUseCase: SyncUseCase,
    private val currentSquadRepository: CurrentSquadRepository

) : BaseViewModel() {

    private val _viewStateLiveData: MutableLiveData<Resource<Boolean>> by lazy {
        MutableLiveData<Resource<Boolean>>()
            .apply { value = Resource.loading() }
    }

    var viewStateLiveData: LiveData<Resource<Boolean>> = _viewStateLiveData

    fun initapp() {
        syncUseCase.syncApp()
            .toState()
            .subscribe(this::setViewState)
            .addTo(compositeDisposable)

        currentSquadRepository.getLocalCountCurrentSquad()
            .flatMap { t ->
                if (t == 0) {
                    currentSquadRepository.insertCurrentSquad(CurrentSquadEntity()).subscribeOn(Schedulers.io()).subscribe().addTo(compositeDisposable)
                }
                Single.just(0)
            }
            .subscribeOn(Schedulers.io())
            .subscribe()
            .addTo(compositeDisposable)
    }

    private fun setViewState(res : Resource<Boolean>?, throwable: Throwable?) {
        res?.let {
            _viewStateLiveData.value = it
        }
        throwable?.let {
            it.printStackTrace()
            _viewStateLiveData.value = Resource.error(it.localizedMessage, null)
        }

    }

    override fun onFirsTimeUiCreate(bundle: Bundle?) {
    }

}