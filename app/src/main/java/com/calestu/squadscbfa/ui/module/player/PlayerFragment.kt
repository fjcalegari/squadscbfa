package com.calestu.squadscbfa.ui.module.player
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.calestu.squadscbfa.R
import com.calestu.squadscbfa.databinding.FragmentPlayersBinding
import com.calestu.squadscbfa.ui.base.Status
import com.calestu.squadscbfa.ui.base.fragment.BaseViewModelFragment
import com.calestu.squadscbfa.ui.module.player.adapter.PlayersPagerAdapter
import com.calestu.squadscbfa.util.ext.watch

class PlayerFragment : BaseViewModelFragment<FragmentPlayersBinding, PlayerViewModel>() {

    private lateinit var playersPagerAdapter : PlayersPagerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            viewmodel = viewModel
        }

        playersPagerAdapter = PlayersPagerAdapter(viewModel, dataBindingComponent)

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

    override fun getViewModelClass(): Class<PlayerViewModel> = PlayerViewModel::class.java
    override fun getLayout(): Int = R.layout.fragment_players

}