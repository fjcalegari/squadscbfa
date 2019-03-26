package com.calestu.squadscbfa.ui.module.mysquad
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.calestu.squadscbfa.R
import com.calestu.squadscbfa.databinding.FragmentMysquadBinding
import com.calestu.squadscbfa.ui.base.ItemClickListener
import com.calestu.squadscbfa.ui.base.fragment.BaseViewModelFragment
import com.calestu.squadscbfa.ui.module.mysquad.adapter.MySquadPagerAdapter
import com.calestu.squadscbfa.ui.module.mysquad.model.MySquadItemModelView

class MySquadFragment : BaseViewModelFragment<FragmentMysquadBinding, MySquadViewModel>() {

    private val squadsPagerAdapter = MySquadPagerAdapter(itemClickListener, dataBindingComponent)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            viewmodel = viewModel
        }

        with(binding.rvSquads) {
            layoutManager = LinearLayoutManager(context)
            adapter = squadsPagerAdapter
        }

        with(viewModel) {
//            onClickedFormationEvent.watch(viewLifecycleOwner) {
//                openFormation(it, FormationFlowType.SQUAD_ADD)
//            }
//            players.watch(viewLifecycleOwner){
//                if (it.status == Status.SUCCESS) {
//                    squadsPagerAdapter.submitList(it.data)
//                }
//            }
        }

    }

//    private val itemClickListener = object : ItemClickListener<MySquadItemModelView> {
//        override fun onItemClick(v: View, item: MySquadItemModelView) {
//            viewModel.clickedSquad(item)
//        }
//    }
    private val itemClickListener: ItemClickListener<MySquadItemModelView>
        get() = object : ItemClickListener<MySquadItemModelView> {
            override fun onItemClick(v: View, item: MySquadItemModelView) {
                viewModel.clickedSquad(item)
            }
        }

    override fun getViewModelClass(): Class<MySquadViewModel> = MySquadViewModel::class.java
    override fun getLayout(): Int = R.layout.fragment_mysquad

}