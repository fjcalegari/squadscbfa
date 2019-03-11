package com.calestu.squadscbfa.util.ext

import androidx.lifecycle.*
import com.calestu.squadscbfa.ui.base.State
import io.reactivex.Flowable
import org.reactivestreams.Publisher
import timber.log.Timber

inline fun <T> LiveData<T>.watch(owner: LifecycleOwner, crossinline observer: (T) -> Unit) {
    this.observe(owner, Observer { observer(it) })
}

fun <X, Y> LiveData<X>.switchMap(func: (X) -> LiveData<Y>)
    = Transformations.switchMap(this, func)

fun <T> LiveData<T>.getDistinct(): LiveData<T> {
    val distinctLiveData = MediatorLiveData<T>()
    distinctLiveData.addSource(this, object : Observer<T> {
        private var initialized = false
        private var lastObj: T? = null
        override fun onChanged(obj: T?) {
            if (!initialized) {
                initialized = true
                lastObj = obj
                distinctLiveData.postValue(lastObj)
            } else if ((obj == null && lastObj != null)
                || obj != lastObj) {
                lastObj = obj
                distinctLiveData.postValue(lastObj)
            }
        }
    })
    return distinctLiveData
}

fun <T> Publisher<T>.toLiveData() = LiveDataReactiveStreams.fromPublisher(this) as LiveData<T>

fun <T> Flowable<T>.toState(): Flowable<State<T>> {
    return compose { item ->
        item
            .map { State.success(it) }
            .startWith(State.loading())
            .onErrorReturn { e -> Timber.e(e); State.error(e.message ?: "Unknown Error", e) }
    }
}

fun defaultErrorHandler(): (Throwable) -> Unit = { e -> Timber.e(e) }
