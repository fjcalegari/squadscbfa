package com.calestu.squadscbfa.ui.module.player.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.calestu.squadscbfa.databinding.ItemPlayerBinding
import com.calestu.squadscbfa.ui.base.ItemClickListener
import com.calestu.squadscbfa.ui.module.player.model.PlayerItemModelView

class PlayersPagerAdapter(
    private val itemClickListener: ItemClickListener<PlayerItemModelView>,
    private val dataBindingComponent: DataBindingComponent
) : ListAdapter<PlayerItemModelView, PlayersPagerAdapter.ItemHolder>(PlayerItemModelView.diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(ItemPlayerBinding.inflate(LayoutInflater.from(parent.context), parent, false, dataBindingComponent))
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(getItem(position), itemClickListener)
    }

    class ItemHolder(val binding : ItemPlayerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(modelView: PlayerItemModelView?, clickListener: ItemClickListener<PlayerItemModelView>) {
            with(binding) {
                modelView?.let {
                    player = it
                    itemView.setOnClickListener { v -> clickListener.onItemClick(v, it) }
                    executePendingBindings()
                }
            }
        }
    }

}