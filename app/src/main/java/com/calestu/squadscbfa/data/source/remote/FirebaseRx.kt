package com.calestu.squadscbfa.data.source.remote

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import io.reactivex.Single

fun DatabaseReference.observeSingleValueEvent() : Single<DataSnapshot> {

    return Single.create { emitter ->
        addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                emitter.onSuccess(dataSnapshot)
            }
            override fun onCancelled(databaseError: DatabaseError) {
                emitter.onError(databaseError.toException())
            }
        })
    }
}

//val connectedRef = FirebaseDatabase.getInstance().getReference(".info/connected")
//connectedRef.addValueEventListener(object : ValueEventListener {
//    override fun onCancelled(databaseError: DatabaseError) {
//        Timber.d("onCancelled: ")
//    }
//
//    override fun onDataChange(dataSnapshot: DataSnapshot) {
//        val connected :Boolean?  = dataSnapshot.getValue(Boolean::class.java)
//        Timber.d("onDataChange.conn: $connected")
//    }
//}
//
//)