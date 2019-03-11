package com.calestu.squadscbfa.di.module

import android.app.Application
import android.content.Context
import com.calestu.squadscbfa.di.builder.viewmodel.ViewModelBuilder
import com.calestu.squadscbfa.di.qualifier.ApplicationContext
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelBuilder::class])
class AppModule {

    @Provides
    @Singleton
    @ApplicationContext
    fun provideApplicationContext(application: Application):Context = application

}