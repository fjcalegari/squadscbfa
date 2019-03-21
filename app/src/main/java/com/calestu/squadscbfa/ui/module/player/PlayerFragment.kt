package com.calestu.squadscbfa.ui.module.player
import android.os.Bundle
import android.view.View
import com.calestu.squadscbfa.R
import com.calestu.squadscbfa.databinding.FragmentPlayersBinding
import com.calestu.squadscbfa.ui.base.fragment.BaseViewModelFragment

class PlayerFragment : BaseViewModelFragment<FragmentPlayersBinding, PlayerViewModel>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            viewmodel = viewModel
//            squad = viewModel.currentSquadLiveData
        }

        with(viewModel) {
//            onClickedFormationEvent.watch(viewLifecycleOwner) {
//                openFormation(it, FormationFlowType.SQUAD_ADD)
//            }
        }

    }

//    override fun openFormation(indexSelected: Int, flowType: FormationFlowType) {
//        fragmentManager?.let {
//            FormationFragment.newInstance(indexSelected, flowType).show(it, "SquadAddFormation")
//        } ?: Timber.e("fragmentManager is null ")
//    }

    override fun getViewModelClass(): Class<PlayerViewModel> = PlayerViewModel::class.java
    override fun getLayout(): Int = R.layout.fragment_players

}