package com.calestu.squadscbfa.ui.base.fragment

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.calestu.squadscbfa.di.qualifier.ActivityContext
import com.calestu.squadscbfa.di.qualifier.ActivityFragmentManager
import com.calestu.squadscbfa.di.qualifier.ChildFragmentManager
import dagger.Module
import dagger.Provides

@Module
abstract class BaseFragmentModule<T: Fragment> {

    @Provides
    @ActivityContext
    fun provideContext(fragment: T): Context = fragment.context!!

    @Provides
    @ActivityFragmentManager
    fun provideActivityFragmentManager(activity: FragmentActivity): FragmentManager = activity.supportFragmentManager

    @Provides
    @ChildFragmentManager
    fun provideChildFragmentManager(fragment: T): FragmentManager {
        return fragment.childFragmentManager
    }

    @Provides
    fun provideActivity(fragment: T): FragmentActivity {
        return fragment.activity!!
    }

}