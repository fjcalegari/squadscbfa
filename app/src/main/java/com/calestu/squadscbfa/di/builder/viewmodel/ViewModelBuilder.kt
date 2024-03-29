package com.calestu.squadscbfa.di.builder.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.calestu.squadscbfa.ui.base.activity.MainViewModel
import com.calestu.squadscbfa.ui.module.formation.FormationViewModel
import com.calestu.squadscbfa.ui.module.home.HomeViewModel
import com.calestu.squadscbfa.ui.module.mysquad.MySquadViewModel
import com.calestu.squadscbfa.ui.module.player.PlayerViewModel
import com.calestu.squadscbfa.ui.module.squad.edit.SquadEditViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelBuilder {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MySquadViewModel::class)
    abstract fun bindMySquadViewModel(viewModel: MySquadViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SquadEditViewModel::class)
    abstract fun bindSquadEditViewModel(viewModel: SquadEditViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FormationViewModel::class)
    abstract fun bindFormationViewModel(viewModel: FormationViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PlayerViewModel::class)
    abstract fun bindPlayerViewModel(viewModel: PlayerViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory( factory: AppViewModelFactory):
            ViewModelProvider.Factory

}