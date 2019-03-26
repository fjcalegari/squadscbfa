package com.calestu.squadscbfa.di.module

import com.calestu.squadscbfa.di.builder.ui.*
import com.calestu.squadscbfa.ui.module.formation.FormationFragment
import com.calestu.squadscbfa.ui.module.home.HomeFragment
import com.calestu.squadscbfa.ui.module.mysquad.MySquadFragment
import com.calestu.squadscbfa.ui.module.player.PlayerFragment
import com.calestu.squadscbfa.ui.module.squad.add.SquadAddFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector(modules = [HomeModule::class])
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector(modules = [MySquadModule::class])
    abstract fun contributeMySquadFragment(): MySquadFragment

    @ContributesAndroidInjector(modules = [SquadAddModule::class])
    abstract fun contributeSquadAddFragment(): SquadAddFragment

    @ContributesAndroidInjector(modules = [FormationModule::class])
    abstract fun contributeFormationFragment(): FormationFragment

    @ContributesAndroidInjector(modules = [PlayerModule::class])
    abstract fun contributePlayerFragment(): PlayerFragment

}
