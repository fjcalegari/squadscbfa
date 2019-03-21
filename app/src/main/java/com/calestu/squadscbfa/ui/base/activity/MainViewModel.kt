package com.calestu.squadscbfa.ui.base.activity

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.calestu.squadscbfa.data.entity.CurrentSquadEntity
import com.calestu.squadscbfa.data.model.type.FormationType
import com.calestu.squadscbfa.data.repository.CurrentSquadRepository
import com.calestu.squadscbfa.data.source.DataManager
import com.calestu.squadscbfa.data.usecase.SyncUseCase
import com.calestu.squadscbfa.ui.base.Resource
import com.calestu.squadscbfa.ui.base.viewmodel.BaseViewModel
import com.calestu.squadscbfa.util.ext.toState
import com.google.firebase.auth.FirebaseUser
import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.SingleTransformer
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val appDataManager: DataManager,
    private val syncUseCase: SyncUseCase,
    private val currentSquadRepository: CurrentSquadRepository

) : BaseViewModel() {

    private val _viewStateLiveData: MutableLiveData<Resource<Boolean>> by lazy {
        MutableLiveData<Resource<Boolean>>()
            .apply { value = Resource.loading(null) }
    }

    var viewStateLiveData: LiveData<Resource<Boolean>> = _viewStateLiveData

    fun initapp() {

        appDataManager.signInAnonymously()
            .compose(afterAuthUser())
            .toState()
            .subscribe(this::setViewState)
            .addTo(compositeDisposable)

        currentSquadRepository.getLocalCountCurrentSquad()
            .flatMap { t ->
                if (t == 0) {
                    currentSquadRepository.insertCurrentSquad(CurrentSquadEntity(formation = FormationType.FORMATION_442)).subscribeOn(Schedulers.io()).subscribe().addTo(compositeDisposable)
                }
                Single.just(0)
            }
            .subscribeOn(Schedulers.io())
            .subscribe()
            .addTo(compositeDisposable)
    }

    private fun afterAuthUser() = object : SingleTransformer<FirebaseUser, Boolean> {
        override fun apply(upstream: Single<FirebaseUser>): SingleSource<Boolean> {
            return upstream.flatMap {
                syncUseCase.syncApp()
            }
        }
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