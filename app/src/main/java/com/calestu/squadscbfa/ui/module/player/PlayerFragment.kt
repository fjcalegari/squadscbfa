package com.calestu.squadscbfa.ui.module.player
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.calestu.squadscbfa.R
import com.calestu.squadscbfa.databinding.FragmentPlayersBinding
import com.calestu.squadscbfa.ui.base.ItemClickListener
import com.calestu.squadscbfa.ui.base.Status
import com.calestu.squadscbfa.ui.base.fragment.BaseViewModelFragment
import com.calestu.squadscbfa.ui.module.player.adapter.PlayersPagerAdapter
import com.calestu.squadscbfa.ui.module.player.model.PlayerItemModelView
import com.calestu.squadscbfa.util.ext.watch
import timber.log.Timber

class PlayerFragment : BaseViewModelFragment<FragmentPlayersBinding, PlayerViewModel>() {

    private val playersPagerAdapter = PlayersPagerAdapter(itemClickListener, dataBindingComponent)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            viewmodel = viewModel
        }

        with(binding.rvPlayers) {
            layoutManager = LinearLayoutManager(context)
            adapter = playersPagerAdapter
        }

        with(viewModel) {
            players.watch(viewLifecycleOwner){
                if (it.status == Status.SUCCESS) {
                    playersPagerAdapter.submitList(it.data?.toMutableList())
                }
            }
        }

    }

    private val itemClickListener: ItemClickListener<PlayerItemModelView>
        get() = object : ItemClickListener<PlayerItemModelView> {
            override fun onItemClick(v: View, item: PlayerItemModelView) {
                viewModel.clickedPlayer(item)
            }
        }

    override fun getViewModelClass(): Class<PlayerViewModel> = PlayerViewModel::class.java
    override fun getLayout(): Int = R.layout.fragment_players

}