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
import com.calestu.squadscbfa.ui.module.player.model.PlayerModelView
import com.calestu.squadscbfa.util.ext.watch

class PlayerFragment : BaseViewModelFragment<FragmentPlayersBinding, PlayerViewModel>() {

    private val playersPagerAdapter = PlayersPagerAdapter(dataBindingComponent)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            viewmodel = viewModel
            players = viewModel.players
        }

        with(binding.rvPlayers) {
            layoutManager = LinearLayoutManager(context)
            adapter = playersPagerAdapter
        }

        with(viewModel) {
//            onClickedFormationEvent.watch(viewLifecycleOwner) {
//                openFormation(it, FormationFlowType.SQUAD_ADD)
//            }
            players.watch(viewLifecycleOwner){
                if (it.status == Status.SUCCESS) {
                    playersPagerAdapter.submitList(it.data)
                }
            }
        }

    }

    private val itemClickListener = object : ItemClickListener<PlayerModelView> {
        override fun onItemClick(v: View, item: PlayerModelView) {
            viewModel.clickedPlayer(item)
        }
    }

    override fun getViewModelClass(): Class<PlayerViewModel> = PlayerViewModel::class.java
    override fun getLayout(): Int = R.layout.fragment_players

}