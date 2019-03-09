package com.calestu.squadscbfa.ui.module.main

import android.os.Bundle
import com.calestu.squadscbfa.R
import com.calestu.squadscbfa.databinding.ActivityMainBinding
import com.calestu.squadscbfa.ui.base.activity.BaseViewModelActivity
import timber.log.Timber

class MainActivity : BaseViewModelActivity<ActivityMainBinding, MainViewModel>(), MainNavigator {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.viewmodel = viewModel

        subscribeUi()

        binding.lifecycleOwner = this

        configureActions()
    }

    private fun subscribeUi() {
    }

    override fun addNewTask() {
        Timber.d("addNewTask: ")
    }

    private fun configureActions() {
        binding.actions = object : MainActionsListener {
            override fun onFabClicked() {
                Timber.d("onFabClicked")
            }
        }
    }

    override fun getLayout(): Int = R.layout.activity_main

}