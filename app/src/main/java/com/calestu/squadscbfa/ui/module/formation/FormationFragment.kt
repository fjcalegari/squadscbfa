package com.calestu.squadscbfa.ui.module.formation

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.calestu.squadscbfa.R
import com.calestu.squadscbfa.data.model.type.FormationType
import com.calestu.squadscbfa.ui.base.ItemClickListener
import com.calestu.squadscbfa.ui.base.fragment.BaseBottomSheetDialogFragment
import com.calestu.squadscbfa.ui.module.formation.adapter.FormationAdapter
import com.calestu.squadscbfa.util.ext.watch
import kotlinx.android.synthetic.main.fragment_formation_list.*
import timber.log.Timber

class FormationFragment: BaseBottomSheetDialogFragment<FormationViewModel>() {

    companion object {
        fun newInstance(
            indexSelected : Int = 0,
            flowType: FormationFlowType) = FormationFragment().apply {
            arguments = Bundle().apply {
                putInt(KEY_INDEX_SELECTED, indexSelected)
                putInt(KEY_FLOW_TYPE, flowType.ordinal)
            }
        }
        const val KEY_INDEX_SELECTED = "KEY_INDEX_SELECTED"
        const val KEY_FLOW_TYPE = "KEY_FLOW_TYPE"
    }

    private val itemClickListener = object : ItemClickListener<FormationType> {
        override fun onItemClick(v: View, item: FormationType) {
            viewModel.formationSelected(item)
        }
    }

    private val formationAdapter: FormationAdapter =
        FormationAdapter(itemClickListener)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with (rvFormation) {
            layoutManager = LinearLayoutManager(context)
            adapter = formationAdapter
        }

        with(viewModel) {
            formations.watch(viewLifecycleOwner) {
                showFormations(it)
            }

            selectedFormationConfirmed.watch(viewLifecycleOwner) {
                formationSelected()
            }
        }

    }

    private fun showFormations(formations: List<FormationType>) {
        formationAdapter.setFormationsList(formations.toMutableList())
    }

    private fun formationSelected() {
        Timber.d("formationSelected: ")
//        (activity as MainActivity?)?.findSelected()
        dismiss()
    }

    override fun showLoading() {
    }

    override fun getLayout(): Int = R.layout.fragment_formation_list
    override fun getViewModelClass(): Class<FormationViewModel> = FormationViewModel::class.java

}