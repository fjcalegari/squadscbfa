package com.calestu.squadscbfa.data.source

import com.calestu.squadscbfa.data.model.type.FormationType
import com.calestu.squadscbfa.data.model.type.RoundType
import com.google.firebase.auth.FirebaseUser
import io.reactivex.Single

interface DataManager {

    fun getCurrentRound() : RoundType

    fun saveCurrentRound(roundType: Int)

    fun getCurrentFormation() : FormationType

    fun saveCurrentFormation(formationType: Int)

    fun userAuthenticated(): Boolean

    fun getCurrentUser() : Single<FirebaseUser>

    fun signInAnonymously() : Single<FirebaseUser>

    fun getUserUid() : String

}