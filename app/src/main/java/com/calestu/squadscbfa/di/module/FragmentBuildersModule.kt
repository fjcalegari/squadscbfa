package com.calestu.squadscbfa.di.module

import com.calestu.squadscbfa.di.builder.ui.HomeModule
import com.calestu.squadscbfa.di.builder.ui.SquadAddModule
import com.calestu.squadscbfa.ui.module.home.HomeFragment
import com.calestu.squadscbfa.ui.module.squad.add.SquadAddFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector(modules = [HomeModule::class])
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector(modules = [SquadAddModule::class])
    abstract fun contributeSquadAddFragment(): SquadAddFragment

}
