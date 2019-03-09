package com.calestu.squadscbfa.util.ext

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

fun <T> ViewModel.mutableLiveDataOf() = MutableLiveData<T>()