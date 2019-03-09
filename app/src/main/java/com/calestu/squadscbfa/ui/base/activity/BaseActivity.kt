package com.calestu.squadscbfa.ui.base.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

abstract class BaseActivity: AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<androidx.fragment.app.Fragment>
    override fun supportFragmentInjector() = androidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(getLayout())
    }

    abstract fun getLayout(): Int

    override fun onBackPressed() {
        super.onBackPressed()
//        overridePendingTransitionExit()
    }

    override fun finish() {
        super.finish()
//        overridePendingTransitionExit()
    }

    override fun startActivity(intent: Intent?) {
        super.startActivity(intent)
//        overridePendingTransitionEnter()
    }

    override fun startActivityForResult(intent: Intent?, requestCode: Int) {
        super.startActivityForResult(intent, requestCode)
//        overridePendingTransitionEnter()
    }

//    private fun overridePendingTransitionEnter() {
//        overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
//    }
//
//    private fun overridePendingTransitionExit() {
//        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right)
//    }

}