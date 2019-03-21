package com.calestu.squadscbfa.data.source

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import durdinapps.rxfirebase2.RxFirebaseAuth
import durdinapps.rxfirebase2.RxFirebaseStorage
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppDataManager @Inject constructor(
    private val context: Context,
    private val firebaseAuth: FirebaseAuth
) : DataManager {

    override fun userAuthenticated(): Boolean {
        return firebaseAuth.currentUser != null
    }

    override fun currentUser(): FirebaseUser? {
        return firebaseAuth.currentUser
    }

    override fun signInAnonymously() : Single<FirebaseUser> {
        Timber.d("signInAnonymously: ")
        remoteCo()
        return if (userAuthenticated()) {
            Timber.d("signInAnonymously: userAuthenticated")
            Single.just(currentUser()!!)
        } else {
            Timber.d("signInAnonymously: signInAnonymously")
            RxFirebaseAuth.signInAnonymously(firebaseAuth)
                .map { it.user }
                .toSingle()
        }
    }

    fun remoteCo() {
        Timber.d("remoteCo: ")

        val storageReference: StorageReference = FirebaseStorage.getInstance().reference.child("coaches.json")

        RxFirebaseStorage.getDownloadUrl(storageReference)
            .doOnSuccess {
                Timber.d("remoteCo.doOnSuccess: $it")
            }
            .doOnComplete {
                Timber.d("remoteCo.doOnComplete")
            }
            .subscribe()
    }

}