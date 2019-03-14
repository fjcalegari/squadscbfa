package com.calestu.squadscbfa.di.module

import com.calestu.squadscbfa.data.executor.BaseSchedulerProvider
import com.calestu.squadscbfa.data.source.remote.FirebaseRemoteSource
import com.calestu.squadscbfa.data.source.remote.RemoteSource
import com.calestu.squadscbfa.data.source.remote.RemoteSourceImpl
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule {

    companion object {
        private const val BASE_URL = "https://newsapi.org/"
        private const val VERSION  = "v2/"
    }

    @Singleton
    @Provides
    fun provideFirebaseRemoteSource(schedulerProvider: BaseSchedulerProvider): FirebaseRemoteSource {
        return FirebaseRemoteSource(schedulerProvider)
    }

    @Singleton
    @Provides
    fun provideNetworkSource(firebaseRemoteSource: FirebaseRemoteSource): RemoteSource {
        return RemoteSourceImpl(firebaseRemoteSource)
    }

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

}