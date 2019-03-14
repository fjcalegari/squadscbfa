package com.calestu.squadscbfa.data.executor

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executors
import javax.inject.Singleton

@Singleton
class SchedulerProvider : BaseSchedulerProvider {

    override fun computation(): Scheduler {
        return Schedulers.computation()
    }

    override fun io(): Scheduler {
        return Schedulers.io()
    }

    override fun multi(): Scheduler {
        return Schedulers.newThread()
    }

    override fun ui(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    override fun networkIO(): Scheduler {
        return Schedulers.from(Executors.newFixedThreadPool(3))
    }

    override fun diskIO(): Scheduler {
        return Schedulers.from(Executors.newSingleThreadExecutor())
    }
}