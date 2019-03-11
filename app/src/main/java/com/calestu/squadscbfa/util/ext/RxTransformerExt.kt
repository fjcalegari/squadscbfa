package com.calestu.squadscbfa.util.ext

import com.calestu.squadscbfa.data.executor.BaseSchedulerProvider
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> singleForUI(schedulerProvider: BaseSchedulerProvider): SingleTransformer<T, T> {
    return SingleTransformer {  it.subscribeOn(schedulerProvider.io()).observeOn(schedulerProvider.ui())}
}

fun <T> singleIO(schedulerProvider: BaseSchedulerProvider): SingleTransformer<T, T> {
    return SingleTransformer {  it.subscribeOn(schedulerProvider.io()).observeOn(schedulerProvider.ui())}
}

fun completableIO(schedulerProvider: BaseSchedulerProvider): CompletableTransformer {
    return CompletableTransformer {  it.subscribeOn(schedulerProvider.io()).observeOn(schedulerProvider.ui())}
}

fun completableForUI(schedulerProvider: BaseSchedulerProvider): CompletableTransformer {
    return CompletableTransformer {  it.subscribeOn(schedulerProvider.io()).observeOn(schedulerProvider.ui())}
}

fun <T> applyObservableSchedulers(): ObservableTransformer<T, T> {
    return ObservableTransformer {  it.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())}
}

fun <T> applySingleSchedulersIO(): SingleTransformer<T, T> {
    return SingleTransformer {  it.subscribeOn(Schedulers.io())}
}

fun applyCompletableSchedulers(): CompletableTransformer {
    return CompletableTransformer {  it.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())}
}

fun <T> applyFlowableSchedulers(): FlowableTransformer<T, T> {
    return FlowableTransformer {  it.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())}
}

fun <T> applyFlowableSchedulersIO(): FlowableTransformer<T, T> {
    return FlowableTransformer {  it.subscribeOn(Schedulers.io())}
}