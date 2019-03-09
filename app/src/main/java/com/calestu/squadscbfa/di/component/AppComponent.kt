package com.calestu.squadscbfa.di.component

import android.app.Application
import com.calestu.squadscbfa.BaseApp
import com.calestu.squadscbfa.di.module.AppModule
import com.calestu.squadscbfa.di.module.BuildersModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        BuildersModule::class
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