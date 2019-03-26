package com.calestu.squadscbfa.ui.module.player.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.calestu.squadscbfa.databinding.ItemPlayerBinding
import com.calestu.squadscbfa.ui.module.player.model.PlayerModelView

class PlayersPagerAdapter(
    private val dataBindingComponent: DataBindingComponent
) : PagedListAdapter<PlayerModelView, PlayersPagerAdapter.ItemHolder>(PlayerModelView.diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(ItemPlayerBinding.inflate(LayoutInflater.from(parent.context), parent, false, dataBindingComponent))
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ItemHolder(val binding : ItemPlayerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(modelView: PlayerModelView?) {
            with(binding) {
                player = modelView
                executePendingBindings()
            }
        }
    }

}