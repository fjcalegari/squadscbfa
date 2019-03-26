package com.calestu.squadscbfa.data.source

import android.content.Context
import com.calestu.squadscbfa.data.model.type.FormationType
import com.calestu.squadscbfa.data.model.type.RoundType
import com.calestu.squadscbfa.data.source.prefs.PreferencesHelper
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import durdinapps.rxfirebase2.RxFirebaseAuth
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppDataManager @Inject constructor(
    private val context: Context,
    private val preferences: PreferencesHelper,
    private val firebaseAuth: FirebaseAuth
) : DataManager {

    override fun getCurrentFormation(): FormationType {
        val formationIndex = preferences.currentFormation
        if (formationIndex == 0) {
            saveCurrentFormation(FormationType.FORMATION_442.index)
            return FormationType.FORMATION_442
        }
        return FormationType.getFormation(formationIndex)
    }

    override fun saveCurrentFormation(formationType: Int) {
        preferences.currentFormation = formationType
    }

    override fun getCurrentRound(): RoundType {
        val index = preferences.currentRound
        return RoundType.getRound(index)
    }

    override fun saveCurrentRound(roundType: Int) {
        preferences.currentRound = roundType
    }

    override fun userAuthenticated(): Boolean {
        return firebaseAuth.currentUser != null
    }

    override fun getCurrentUser(): Single<FirebaseUser> {
        return if (userAuthenticated()) {
            Single.just(firebaseAuth.currentUser)
        } else {
            signInAnonymously()
        }
    }

    override fun signInAnonymously() : Single<FirebaseUser> {
        return RxFirebaseAuth.signInAnonymously(firebaseAuth)
            .map { it.user }
            .toSingle()
    }

    override fun getUserUid(): String {
        return firebaseAuth.currentUser?.uid ?: ""
    }

}