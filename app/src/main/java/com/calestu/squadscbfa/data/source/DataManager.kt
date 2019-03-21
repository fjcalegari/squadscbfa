package com.calestu.squadscbfa.data.source

import com.google.firebase.auth.FirebaseUser
import io.reactivex.Single

interface DataManager {

    fun userAuthenticated(): Boolean

    fun currentUser() : FirebaseUser?

    fun signInAnonymously() : Single<FirebaseUser>

}