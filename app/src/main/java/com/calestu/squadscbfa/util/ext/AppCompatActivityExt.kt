package com.calestu.squadscbfa.util.ext

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

const val ADD_EDIT_RESULT_OK = Activity.RESULT_FIRST_USER + 1

fun AppCompatActivity.replaceFragmentInActivity(frameId: Int, fragment: Fragment, tag: String) {
    supportFragmentManager.transact {
        addToBackStack(null)
        replace(frameId, fragment, tag)
    }
}

fun AppCompatActivity.findFragmentByTag(tag: String): Fragment? = supportFragmentManager.findFragmentByTag(tag)

fun AppCompatActivity.addFragmentToActivity(frameId: Int, fragment: Fragment, tag: String) {
    supportFragmentManager.transact {
        add(frameId, fragment, tag)
    }
}

private inline fun FragmentManager.transact(action: FragmentTransaction.() -> Unit) {
    beginTransaction().apply {
        action()
    }.commitAllowingStateLoss()
}