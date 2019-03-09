package com.calestu.squadscbfa.ui.base.activity

import android.app.Activity
import android.content.Context
import androidx.fragment.app.FragmentManager
import androidx.appcompat.app.AppCompatActivity
import com.calestu.squadscbfa.di.qualifier.ActivityContext
import com.calestu.squadscbfa.di.qualifier.ActivityFragmentManager
import dagger.Module
import dagger.Provides

@Module
abstract class BaseActivityModule<T: AppCompatActivity> {

    @Provides
    @ActivityContext
    fun provideContext(activity: T): Context = activity

    @Provides
    fun provideActivity(activity: T): Activity = activity

    @Provides
    @ActivityFragmentManager
    fun provideFragmentManager(activity: T): androidx.fragment.app.FragmentManager = activity.supportFragmentManager

}