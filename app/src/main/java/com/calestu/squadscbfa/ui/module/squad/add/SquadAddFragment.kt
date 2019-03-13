package com.calestu.squadscbfa.ui.module.squad.add
import android.os.Bundle
import android.view.View
import com.calestu.squadscbfa.R
import com.calestu.squadscbfa.databinding.FragmentSquadAddBinding
import com.calestu.squadscbfa.ui.base.fragment.BaseViewModelFragment

class SquadAddFragment : BaseViewModelFragment<FragmentSquadAddBinding, SquadAddViewModel>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            viewmodel = viewModel
        }

    }

    override fun getViewModelClass(): Class<SquadAddViewModel> = SquadAddViewModel::class.java
    override fun getLayout(): Int = R.layout.fragment_squad_add

}