package com.calestu.squadscbfa.data.source.remote

import com.calestu.squadscbfa.data.executor.BaseSchedulerProvider
import com.calestu.squadscbfa.data.source.remote.model.AppVersionRemoteModel
import com.calestu.squadscbfa.data.source.remote.model.CoachRemoteModel
import com.calestu.squadscbfa.data.source.remote.model.PlayerRemoteModel
import com.calestu.squadscbfa.data.source.remote.model.type.AppVersionRemoteType
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import durdinapps.rxfirebase2.RxFirebaseStorage
import io.reactivex.*
import org.reactivestreams.Publisher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteSourceImpl @Inject constructor(
    private val apiService: ApiService,
    private val schedulerProvider: BaseSchedulerProvider
): RemoteSource {

    override fun getAppVersion(): Single<AppVersionRemoteModel> {
        return getFirebaseStorageDownloadUrl(AppVersionRemoteType.VERSION)
            .compose(getAppVersionRemote())
    }
    private fun getAppVersionRemote() = object : SingleTransformer<String, AppVersionRemoteModel> {
        override fun apply(upstream: Single<String>): SingleSource<AppVersionRemoteModel> {
            return upstream.flatMap {
                apiService.getAppVersion(it)
            }
        }
    }

    override fun getCoaches(): Flowable<List<CoachRemoteModel>> {
        return getFirebaseStorageDownloadUrl(AppVersionRemoteType.COACH)
            .toFlowable()
            .compose(getCoachesRemote())
    }
    private fun getCoachesRemote() = object : FlowableTransformer<String, List<CoachRemoteModel>> {
        override fun apply(upstream: Flowable<String>): Publisher<List<CoachRemoteModel>> {
            return upstream.flatMap {
                apiService.getCoaches(it)
            }
        }
    }

    override fun getPlayers(appVersionRemoteType: AppVersionRemoteType): Flowable<List<PlayerRemoteModel>> {
        return getFirebaseStorageDownloadUrl(appVersionRemoteType)
            .toFlowable()
            .compose(getPlayersRemote())
    }
    private fun getPlayersRemote() = object : FlowableTransformer<String, List<PlayerRemoteModel>> {
        override fun apply(upstream: Flowable<String>): Publisher<List<PlayerRemoteModel>> {
            return upstream.flatMap {
                apiService.getPlayers(it)
            }
        }
    }

    private fun getFirebaseStorageDownloadUrl(appVersionRemoteType: AppVersionRemoteType) : Single<String> {
        val storageReference: StorageReference = FirebaseStorage.getInstance().reference.child(appVersionRemoteType.fileName)
        return RxFirebaseStorage.getDownloadUrl(storageReference)
            .map {
                it.toString()
            }
            .toSingle()
            .subscribeOn(schedulerProvider.networkIO())
            .observeOn(schedulerProvider.networkIO())
    }

}