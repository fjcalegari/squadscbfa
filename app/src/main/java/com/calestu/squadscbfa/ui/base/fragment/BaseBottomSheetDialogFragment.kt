package com.calestu.squadscbfa.ui.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.calestu.squadscbfa.util.autoCleared
import com.calestu.squadscbfa.util.binding.FragmentDataBindingComponent
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomSheetDialogFragment<B: ViewDataBinding> : BottomSheetDialogFragment() {

    var binding by autoCleared<B>()

    var dataBindingComponent: DataBindingComponent = FragmentDataBindingComponent(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val dataBinding: B = DataBindingUtil.inflate(inflater, getLayout(), container, false, dataBindingComponent)
        this.binding = dataBinding
        this.binding.lifecycleOwner = viewLifecycleOwner
        return this.binding.root
    }

    abstract fun getLayout(): Int

    fun getFragmentArguments(): Bundle? {
        return arguments
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

}