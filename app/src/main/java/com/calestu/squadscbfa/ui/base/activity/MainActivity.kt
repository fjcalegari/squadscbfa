package com.calestu.squadscbfa.ui.base.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import com.calestu.squadscbfa.R
import com.calestu.squadscbfa.databinding.ActivityMainBinding
import com.calestu.squadscbfa.ui.base.Resource
import com.calestu.squadscbfa.ui.base.Status
import com.calestu.squadscbfa.util.ext.drawable
import com.calestu.squadscbfa.util.ext.navigateSingleTop
import com.calestu.squadscbfa.util.ext.watch
import com.google.android.material.bottomappbar.BottomAppBar
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import timber.log.Timber
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: MainViewModel

    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme_Main)
        super.onCreate(savedInstanceState)

//        window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
//        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProviders.of(this, this.viewModelFactory).get(MainViewModel::class.java)

        navController = Navigation.findNavController(this, R.id.navHostFragment)
        setSupportActionBar(binding.bottomAppBar)

        with(viewModel) {
            onCreate(intent.extras)
        }

        with(binding) {
            lifecycleOwner = this@MainActivity
            viewmodel = viewModel
        }

        viewModel.viewStateLiveData.watch(this@MainActivity, this::showState)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            invalidateOptionsMenu()
            configureBottomAppBar(destination.id)
        }

        binding.fab.setOnClickListener {
            when (binding.bottomAppBar.fabAlignmentMode) {
                BottomAppBar.FAB_ALIGNMENT_MODE_END -> onBackPressed()
                BottomAppBar.FAB_ALIGNMENT_MODE_CENTER -> showSquadAdd()
            }
        }

        viewModel.initapp()
    }

    private fun configureBottomAppBar(destinationId : Int) {
        Timber.d("configureBottomAppBar: ")
        when (destinationId) {
            R.id.homeFragment -> {
                setBottomAppBarNavigationMenu()
                setFabVisible(true)
                setFabAlignment(BottomAppBar.FAB_ALIGNMENT_MODE_CENTER, R.drawable.icon_add)
            }
            R.id.squadAddFragment -> {
                setFabVisible(false)
                setBottomAppBarNavigationBack()
            }
        }
    }

    private fun showSquadAdd() {
        Timber.d("showSquadAdd: ")
        navController.navigate(R.id.fragmentHomeToSquad)
    }

    private fun showState(state: Resource<Boolean>) {
        Timber.d("showState: ")
        when(state.status) {
            Status.SUCCESS -> showHome()
            Status.LOADING -> showLoading()
            Status.ERROR -> showError(state.message)
        }
    }

    fun showLoading() {
        Timber.d("showLoading: ")
    }

    fun dismissLoading() {
        Timber.d("dismissLoading: ")
    }

    private fun showError(errorMessage: String?) {
        Timber.d("showError: $errorMessage")
    }

    private fun showHome() {
        navController.navigate(
            R.id.homeFragment,
            null,
            NavOptions.Builder()
                .setPopUpTo(
                    R.id.placeholderFragment,
                    true
                )
                .setLaunchSingleTop(true)
                .build()
        )
    }

    private fun setFabAlignment(alignment: Int, @DrawableRes drawable: Int) {
        binding.bottomAppBar.fabAlignmentMode = alignment
        binding.fab.setImageResource(drawable)
    }

    private fun setFabVisible(visible: Boolean) {
        if (visible && binding.fab.isOrWillBeHidden) {
            binding.fab.show()
         } else {
            binding.fab.hide()
        }
    }

    private fun setBottomAppBarNavigationMenu() {
        binding.bottomAppBar.navigationIcon = drawable(R.drawable.icon_menu)
    }

    private fun setBottomAppBarNavigationBack() {
        binding.bottomAppBar.navigationIcon = drawable(R.drawable.icon_back)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        if (binding.bottomAppBar.fabAlignmentMode == BottomAppBar.FAB_ALIGNMENT_MODE_CENTER &&
            binding.fab.isOrWillBeShown) {
            menuInflater.inflate(R.menu.menu_main, menu)
        }
        return true
    }

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {

        return when (menuItem.itemId) {
            android.R.id.home -> {
                navController.currentDestination?.let {
                    when (it.id) {
                        R.id.homeFragment -> Timber.d("currentDestination: homeFragment")
                        R.id.squadAddFragment -> onBackPressed()
                    }
                }
                true
            }
            else -> super.onOptionsItemSelected(menuItem)
        }
    }

    override fun onSupportNavigateUp() = Navigation.findNavController(this, R.id.navHostFragment).navigateUp()

    override fun supportFragmentInjector() = dispatchingAndroidInjector

}