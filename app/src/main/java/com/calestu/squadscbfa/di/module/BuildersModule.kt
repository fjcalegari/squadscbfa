package com.calestu.squadscbfa.di.module

import com.calestu.squadscbfa.ui.module.main.MainActivity
import com.calestu.squadscbfa.ui.module.main.MainModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersModule {

    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun provideMainActivity(): MainActivity

}