//package com.calestu.squadscbfa.ui.base.fragment
//
//import android.os.Bundle
//import android.view.View
//import androidx.databinding.ViewDataBinding
//import androidx.lifecycle.ViewModelProvider
//import androidx.lifecycle.ViewModelProviders
//import com.calestu.squadscbfa.di.builder.Injectable
//import com.calestu.squadscbfa.ui.base.activity.MainActivity
//import com.calestu.squadscbfa.ui.base.viewmodel.BaseViewModel
//import javax.inject.Inject
//
//@Suppress("UNCHECKED_CAST")
//abstract class OldBaseViewModelBottomSheetDialogFragment<B: ViewDataBinding, VM: BaseViewModel>: OldBaseBottomSheetDialogFragment<B>(), Injectable {
//
//    @Inject
//    lateinit var viewModelFactory: ViewModelProvider.Factory
//
//    lateinit var viewModel: VM
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        this.viewModel = ViewModelProviders.of(this, this.viewModelFactory).get(getViewModelClass())
//
//        this.viewModel.onCreate(getFragmentArguments())
//    }
//
//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        if (activity !is MainActivity) {
//            throw IllegalStateException("All fragment's container must extend BaseActivity")
//        }
//    }
//
//    abstract fun getViewModelClass(): Class<VM>
//
//}