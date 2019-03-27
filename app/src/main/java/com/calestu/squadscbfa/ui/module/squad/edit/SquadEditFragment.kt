package com.calestu.squadscbfa.ui.module.squad.edit
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.calestu.squadscbfa.R
import com.calestu.squadscbfa.databinding.FragmentSquadEditBinding
import com.calestu.squadscbfa.ui.base.Resource
import com.calestu.squadscbfa.ui.base.Status
import com.calestu.squadscbfa.ui.base.fragment.BaseViewModelFragment
import com.calestu.squadscbfa.ui.module.formation.FormationFlowType
import com.calestu.squadscbfa.ui.module.formation.FormationFragment
import com.calestu.squadscbfa.ui.module.squad.edit.model.SquadEditModelView
import com.calestu.squadscbfa.util.ext.watch
import timber.log.Timber

class SquadEditFragment :
    BaseViewModelFragment<FragmentSquadEditBinding, SquadEditViewModel>(),
    SquadEditNavigator{

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            viewmodel = viewModel
        }

        with(viewModel) {
            squad.watch(viewLifecycleOwner) {
                currentState(it)
            }
            onClickedFormationEvent.watch(viewLifecycleOwner) {
                openFormation(it, FormationFlowType.SQUAD_ADD)
            }
            onClickedAddPlayerEvent.watch(viewLifecycleOwner) {
                showPlayers(it)
            }
        }

    }

    override fun openFormation(indexSelected: Int, flowType: FormationFlowType) {
        fragmentManager?.let {
            FormationFragment.newInstance(indexSelected, flowType).show(it, "SquadAddFormation")
        } ?: Timber.e("fragmentManager is null ")
    }

    override fun showPlayers(args: Pair<String, Int>) {
        Timber.d("showPlayers: ")
        findNavController().navigate(SquadEditFragmentDirections.editSquadToPlayer(args.first, args.second))
    }

    private fun currentState(state: Resource<SquadEditModelView>) {
        when(state.status) {
            Status.ERROR -> showError(state.message)
        }
    }

    private fun showError(errorMessage: String?) {
        Timber.d("showError: $errorMessage")
    }

    override fun getViewModelClass(): Class<SquadEditViewModel> = SquadEditViewModel::class.java
    override fun getLayout(): Int = R.layout.fragment_squad_edit

}