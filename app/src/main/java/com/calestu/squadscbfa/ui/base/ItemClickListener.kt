package com.calestu.squadscbfa.ui.base

import android.view.View

interface ItemClickListener<T> {
    fun onItemClick(v: View, item : T)
}