package com.calestu.squadscbfa.ui.module.squad.add
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.calestu.squadscbfa.R
import com.calestu.squadscbfa.databinding.FragmentSquadAddBinding
import com.calestu.squadscbfa.ui.base.fragment.BaseViewModelFragment
import com.calestu.squadscbfa.ui.module.formation.FormationFlowType
import com.calestu.squadscbfa.ui.module.formation.FormationFragment
import com.calestu.squadscbfa.util.ext.watch
import timber.log.Timber

class SquadAddFragment : BaseViewModelFragment<FragmentSquadAddBinding, SquadAddViewModel>(), SquadAddNavigator {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            viewmodel = viewModel
            squad = viewModel.currentSquadLiveData
        }
        
        with(viewModel) {
            onClickedFormationEvent.watch(viewLifecycleOwner) {
                openFormation(it, FormationFlowType.SQUAD_ADD)
            }

            onClickedAddPlayerEvent.watch(viewLifecycleOwner) {
                showPlayers(it)
            }

        }

    }

    override fun showPlayers(args: Pair<String, Int>) {
        Timber.d("showPlayers: ")
        findNavController().navigate(SquadAddFragmentDirections.fragmentAddSquadToPlayer(args.first, args.second))
    }

    override fun openFormation(indexSelected: Int, flowType: FormationFlowType) {
        fragmentManager?.let {
            FormationFragment.newInstance(indexSelected, flowType).show(it, "SquadAddFormation")
        } ?: Timber.e("fragmentManager is null ")
    }

    override fun getViewModelClass(): Class<SquadAddViewModel> = SquadAddViewModel::class.java
    override fun getLayout(): Int = R.layout.fragment_squad_add

}