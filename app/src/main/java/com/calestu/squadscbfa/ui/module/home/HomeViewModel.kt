package com.calestu.squadscbfa.ui.module.home

import android.os.Bundle
import com.calestu.squadscbfa.ui.base.viewmodel.BaseViewModel
import timber.log.Timber
import javax.inject.Inject

class HomeViewModel @Inject constructor(

) : BaseViewModel() {

//    private val pageSelectedSubject: PublishSubject<QuoteModelView> = PublishSubject.create()
//    private val toggleFavoriteSubject: PublishSubject<QuoteModelView> = PublishSubject.create()
//
//    private val workInfoStateSubject: PublishSubject<WorkInfo.State> = PublishSubject.create()
//
//    private val workManager = WorkManager.getInstance()
//
//    private val oneTimeSyncWork : OneTimeWorkRequest by lazy {
//        OneTimeWorkRequest.Builder(SyncWorker::class.java)
//            .build()
//    }
//
//    private val _currentItemLiveData = MutableLiveData<QuoteModelView>()
//
//    private val _viewStateLiveData: MutableLiveData<HomeViewState> by lazy {
//        MutableLiveData<HomeViewState>()
//            .apply { value = HomeViewState.LOADING() }
//    }
//
//    val viewStateLiveData:LiveData<HomeViewState> = _viewStateLiveData
//
//    val workInfoLiveData:LiveData<WorkInfo> = workManager.getWorkInfoByIdLiveData(oneTimeSyncWork.id)
//
//    val quotesLiveData :LiveData<PagedList<QuoteModelView>> = Transformations
//        .switchMap(_viewStateLiveData) {
//            Timber.d("quotesLiveData : _viewStateLiveData")
//            if (it.viewState == HomeViewState.ViewState.LOADING) {
//                AbsentLiveData.create()
//            } else {
//                quotesDao.getDistinctAllQuotes()
//            }
//        }
//
//    val currentItemLiveData :LiveData<QuoteModelView> = _currentItemLiveData.getDistinct()

    init {
        Timber.d("init")

//        startWorkSync()
//
//        pageSelectedSubject()
//
//        workInfoStateSubject()
    }

//    private fun toggleFavoriteSubject(id: Int, favorite: Boolean) {
//        addDisposable(
//            quotesRepository.toggleFavorite(id, favorite)
//                .subscribe(
//                    { Timber.d("toggleFavoriteSubject: success")},
//                    { Timber.e(it, "toggleFavoriteSubject: ")})
//        )
//    }
//
//    private fun pageSelectedSubject() {
//        pageSelectedSubject
//            .subscribeOn(Schedulers.io())
//            .doOnError {
//                Timber.e(it,it.localizedMessage)
//            }
//            .doOnNext {
//                Timber.d("doOnNext: $it")
////                _currentItemLiveData.value = it
//            }
//            .flatMapCompletable {
//                Timber.d("flatMapCompletable")
//                quotesRepository.saveQuoteReaded(it.entryid)
//            }
//            .subscribe()
//            .addTo(compositeDisposable)
//
//    }
//
//    private fun workInfoStateSubject() {
//        workInfoStateSubject
//            .subscribeOn(Schedulers.io())
//            .doOnError {
//                Timber.e(it,it.localizedMessage)
//            }
//            .map {
//                if (it.isFinished) {
//                    return@map HomeViewState.SUCCESS(emptyList())
//                }
//                return@map currentViewState()
//            }
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(
//                {setViewState(it)},
//                {Timber.e(it,it.localizedMessage)}
//            )
//            .addTo(compositeDisposable)
//
//    }
//
//    private fun startWorkSync() {
//        workManager.enqueue(oneTimeSyncWork)
//    }
//
//    fun onWorkInfoStateChanged(workInfo: WorkInfo) {
//        Timber.d("onWorkInfoStateChanged: $workInfo")
//        workInfo?.let {
//            workInfoStateSubject.onNext(it.state)
//        }
//    }
//
//    fun onCurrentItemChanged(quoteModelView: QuoteModelView) {
//        pageSelectedSubject.onNext(quoteModelView)
//    }
//
//    fun onToggleFavorite(quoteModelView: QuoteModelView?) {
//        Timber.d("onToggleFavorite: $quoteModelView")
//        quoteModelView?.let {
////            toggleFavoriteSubject.onNext(it)
//            toggleFavoriteSubject(it.entryid, it.favorite.not())
//        }
//    }
//
//    private fun setViewState(homeViewState: HomeViewState?) {
//        homeViewState?.let {
//            if (it != currentViewState()) {
//                _viewStateLiveData.value = it
//            }
//        }
//    }
//
//    private fun currentViewState() = _viewStateLiveData.value

    override fun onFirsTimeUiCreate(bundle: Bundle?) {
        Timber.d("onFirsTimeUiCreate: ")
    }

}