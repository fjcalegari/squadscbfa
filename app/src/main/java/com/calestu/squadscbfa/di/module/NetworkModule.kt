package com.calestu.squadscbfa.di.module

import com.calestu.squadscbfa.BuildConfig
import com.calestu.squadscbfa.data.executor.BaseSchedulerProvider
import com.calestu.squadscbfa.data.source.remote.*
import com.calestu.squadscbfa.data.source.remote.factories.RxErrorHandlingCallAdapterFactory
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder().apply {
            connectTimeout(RemoteConstants.TIME_OUT_API, TimeUnit.SECONDS)
            readTimeout(RemoteConstants.TIME_OUT_API, TimeUnit.SECONDS)
            writeTimeout(RemoteConstants.TIME_OUT_API, TimeUnit.SECONDS)
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
            if (BuildConfig.DEBUG) {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            }
            addInterceptor(httpLoggingInterceptor)
        }.build()
    }

    @Provides
    @Singleton
    fun provideRestAdapter(client: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://localhost/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Singleton
    @Provides
    fun provideFirebaseRemoteSource(schedulerProvider: BaseSchedulerProvider): FirebaseRemoteSource {
        return FirebaseRemoteSource(schedulerProvider)
    }

    @Singleton
    @Provides
    fun provideNetworkSource(
        apiService: ApiService,
        schedulerProvider: BaseSchedulerProvider
    ): RemoteSource {
        return RemoteSourceImpl(apiService, schedulerProvider)
    }

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

}