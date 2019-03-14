package com.calestu.squadscbfa.data.source.remote

import com.calestu.squadscbfa.data.executor.BaseSchedulerProvider
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import durdinapps.rxfirebase2.RxFirebaseDatabase
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseRemoteSource @Inject constructor(
    private val schedulerProvider: BaseSchedulerProvider
){

    private val firebaseDatabase = FirebaseDatabase.getInstance()

    fun observeSingleValueEvent(databaseChild : DatabaseChildType) : Single<DataSnapshot> {
        return RxFirebaseDatabase.observeSingleValueEvent(firebaseDatabase.reference.child(databaseChild.child))
            .subscribeOn(schedulerProvider.networkIO())
            .observeOn(schedulerProvider.diskIO())
            .toSingle()
    }

}