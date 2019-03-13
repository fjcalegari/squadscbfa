package com.calestu.squadscbfa.di.builder.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.calestu.squadscbfa.ui.base.activity.MainViewModel
import com.calestu.squadscbfa.ui.module.home.HomeViewModel
import com.calestu.squadscbfa.ui.module.squad.add.SquadAddViewModel
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
    @ViewModelKey(SquadAddViewModel::class)
    abstract fun bindSquadAddViewModel(viewModel: SquadAddViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory( factory: AppViewModelFactory):
            ViewModelProvider.Factory

}