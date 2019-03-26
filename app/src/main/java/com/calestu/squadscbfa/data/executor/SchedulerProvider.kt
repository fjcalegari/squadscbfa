package com.calestu.squadscbfa.data.executor

import com.calestu.squadscbfa.util.AppExecutors
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SchedulerProvider @Inject constructor(
    private val appExecutors: AppExecutors
) : BaseSchedulerProvider {

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
        return Schedulers.from(appExecutors.networkIO())
    }

    override fun diskIO(): Scheduler {
        return Schedulers.from(appExecutors.diskIO())
    }
}