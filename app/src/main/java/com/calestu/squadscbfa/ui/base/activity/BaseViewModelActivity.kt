package com.calestu.squadscbfa.ui.base.activity

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import android.os.Bundle
import com.calestu.squadscbfa.ui.base.viewmodel.BaseViewModel
import java.lang.reflect.ParameterizedType
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
abstract class BaseViewModelActivity<B: ViewDataBinding, VM: BaseViewModel>: BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModel: VM

    lateinit var binding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.binding = DataBindingUtil.setContentView(this, getLayout())

        val viewModelClass: Class<VM> = ((javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[1]) as Class<VM>
        this.viewModel = ViewModelProviders.of(this, this.viewModelFactory).get(viewModelClass)

        this.viewModel.onCreate(intent.extras)
    }

}