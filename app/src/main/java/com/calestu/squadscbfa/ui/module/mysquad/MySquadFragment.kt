package com.calestu.squadscbfa.ui.module.mysquad
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.calestu.squadscbfa.R
import com.calestu.squadscbfa.databinding.FragmentMysquadBinding
import com.calestu.squadscbfa.ui.base.ItemClickListener
import com.calestu.squadscbfa.ui.base.Resource
import com.calestu.squadscbfa.ui.base.Status
import com.calestu.squadscbfa.ui.base.fragment.BaseViewModelFragment
import com.calestu.squadscbfa.ui.module.mysquad.adapter.MySquadPagerAdapter
import com.calestu.squadscbfa.ui.module.mysquad.model.MySquadItemModelView
import com.calestu.squadscbfa.util.ext.watch
import timber.log.Timber

class MySquadFragment :
    BaseViewModelFragment<FragmentMysquadBinding, MySquadViewModel>(),
    MySquadNavigator {

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
            mySquadsLiveData.watch(viewLifecycleOwner) {
                currentState(it)
            }
            onClickedAddSquadEvent.watch(viewLifecycleOwner) {
                openMySquad("")
            }
            onClickedSquadEvent.watch(viewLifecycleOwner) {
                openMySquad(it)
            }
        }

    }

    override fun openMySquad(squadEntryid: String) {
        Timber.d("openMySquad: $squadEntryid")
        findNavController().navigate(MySquadFragmentDirections.mySquadToEdit(squadEntryid))
    }

    private fun currentState(state: Resource<PagedList<MySquadItemModelView>>) {
        when(state.status) {
            Status.SUCCESS -> {
                state.data?.let {
                    if (!it.isNullOrEmpty()) {
                        squadsPagerAdapter.submitList(it)
                    }
                }
            }
            Status.ERROR -> showError(state.message)
        }
    }

    private fun showError(errorMessage: String?) {
        Timber.d("showError: $errorMessage")
    }

    private val itemClickListener: ItemClickListener<MySquadItemModelView>
        get() = object : ItemClickListener<MySquadItemModelView> {
            override fun onItemClick(v: View, item: MySquadItemModelView) {
                viewModel.clickedSquad(item)
            }
        }

    override fun getViewModelClass(): Class<MySquadViewModel> = MySquadViewModel::class.java
    override fun getLayout(): Int = R.layout.fragment_mysquad

}