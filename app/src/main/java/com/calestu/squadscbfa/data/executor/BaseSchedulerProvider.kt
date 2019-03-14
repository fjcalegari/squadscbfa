package com.calestu.squadscbfa.data.executor

import io.reactivex.Scheduler

interface BaseSchedulerProvider {

    fun computation(): Scheduler

    fun multi(): Scheduler

    fun io(): Scheduler

    fun ui(): Scheduler

    fun networkIO(): Scheduler

    fun diskIO(): Scheduler
}