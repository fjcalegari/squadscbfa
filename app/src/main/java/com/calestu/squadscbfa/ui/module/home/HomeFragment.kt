package com.calestu.squadscbfa.ui.module.home
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.calestu.squadscbfa.R
import com.calestu.squadscbfa.databinding.FragmentHomeBinding
import com.calestu.squadscbfa.ui.base.fragment.BaseViewModelFragment
import com.calestu.squadscbfa.util.ext.watch
import timber.log.Timber

class HomeFragment :
    BaseViewModelFragment<FragmentHomeBinding, HomeViewModel>(),
        HomeNavigator
{

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            viewmodel = viewModel
        }

        with(viewModel) {
            onClickedMySquadsEvent.watch(viewLifecycleOwner) {
                openMySquads()
            }
        }

    }

    override fun openMySquads() {
        Timber.d("openMySquads: ")
        findNavController().navigate(R.id.fragmentHomeToMySquad)
    }

    override fun getViewModelClass(): Class<HomeViewModel> = HomeViewModel::class.java
    override fun getLayout(): Int = R.layout.fragment_home

}