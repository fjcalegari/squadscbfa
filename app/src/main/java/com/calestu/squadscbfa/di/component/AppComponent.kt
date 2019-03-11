package com.calestu.squadscbfa.di.component

import android.app.Application
import com.calestu.squadscbfa.BaseApp
import com.calestu.squadscbfa.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        LocalModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        UseCaseModule::class,
        MainActivityModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: BaseApp)

}