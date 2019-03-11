package com.calestu.squadscbfa.data.source.remote

import com.google.firebase.database.FirebaseDatabase

abstract class FirebaseRemoteSource {

    private val firebaseDatabase = FirebaseDatabase.getInstance()

    fun versionChild() = firebaseDatabase.reference.child("version")

}