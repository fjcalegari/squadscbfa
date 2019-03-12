package com.calestu.squadscbfa.ui.base.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.calestu.squadscbfa.R
import com.calestu.squadscbfa.databinding.ActivityMainBinding
import com.calestu.squadscbfa.ui.base.Resource
import com.calestu.squadscbfa.ui.base.Status
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

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme_Main)
        super.onCreate(savedInstanceState)

        window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProviders.of(this, this.viewModelFactory).get(MainViewModel::class.java)

        with(viewModel) {
            onCreate(intent.extras)
        }

        with(binding) {
            lifecycleOwner = this@MainActivity
            viewmodel = viewModel
        }

        viewModel.viewStateLiveData.watch(this@MainActivity, this::showState)

        setSupportActionBar(binding.bottomAppBar)
        Navigation.findNavController(
            this,
            R.id.navHostFragment
        ).addOnDestinationChangedListener { controller, destination, arguments ->
            invalidateOptionsMenu()
//            when (destination.id) {
//                R.id.listFragment -> {
//                    setFabAlignment(BottomAppBar.FAB_ALIGNMENT_MODE_CENTER, R.drawable.icon_add)
//                    slideUpBar()
//                }
//                R.id.storyFragment -> setFabAlignment(BottomAppBar.FAB_ALIGNMENT_MODE_END, R.drawable.icon_back)
//                R.id.searchFavoritesFragment -> setFabAlignment(BottomAppBar.FAB_ALIGNMENT_MODE_END, R.drawable.icon_back)
//            }
        }
        binding.fab.setOnClickListener {
            when (binding.bottomAppBar.fabAlignmentMode) {
                BottomAppBar.FAB_ALIGNMENT_MODE_END -> onBackPressed()
//                BottomAppBar.FAB_ALIGNMENT_MODE_CENTER -> showEntriesAddBottomDialog(true)
            }
        }

    }

    private fun showState(state: Resource<Boolean>) {
        when(state.status) {
            Status.SUCCESS -> showHome()
            Status.LOADING -> showLoading()
            Status.ERROR -> showError(state.message)
        }
    }

    private fun showLoading() {
        Timber.d("showLoading: ")
    }

    private fun showError(errorMessage: String?) {
        Timber.d("showError: $errorMessage")
    }

    private fun showHome() {
        Timber.d("showHome: ")
        Navigation.findNavController(this, R.id.navHostFragment).navigate(R.id.homeFragment)
    }

//    private fun setFabAlignment(alignment: Int, @DrawableRes drawable: Int) {
//        binding.bottomAppBar.fabAlignmentMode = alignment
//        binding.bottomAppBar.fabAnimationMode = BottomAppBar.FAB_ANIMATION_MODE_SLIDE
//        binding.fab.setImageResource(drawable)
//    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        if (binding.bottomAppBar.fabAlignmentMode == BottomAppBar.FAB_ALIGNMENT_MODE_CENTER) {
            menuInflater.inflate(R.menu.menu_main, menu)
        }
        return true
    }

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            android.R.id.home -> onBackPressed()
        }

        return true
    }

    override fun onSupportNavigateUp() = Navigation.findNavController(this, R.id.navHostFragment).navigateUp()

    fun showBackButton() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun supportFragmentInjector() = dispatchingAndroidInjector

}