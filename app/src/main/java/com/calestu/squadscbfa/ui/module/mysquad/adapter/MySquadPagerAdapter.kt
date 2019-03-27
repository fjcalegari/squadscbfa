package com.calestu.squadscbfa.ui.module.mysquad.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.calestu.squadscbfa.databinding.ItemMysquadBinding
import com.calestu.squadscbfa.ui.base.ItemClickListener
import com.calestu.squadscbfa.ui.module.mysquad.model.MySquadItemModelView

class MySquadPagerAdapter(
    private val itemClickListener: ItemClickListener<MySquadItemModelView>,
    private val dataBindingComponent: DataBindingComponent
) : PagedListAdapter<MySquadItemModelView, MySquadPagerAdapter.ItemHolder>(MySquadItemModelView.diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(ItemMysquadBinding.inflate(LayoutInflater.from(parent.context), parent, false, dataBindingComponent))
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(getItem(position), itemClickListener)
    }

    class ItemHolder(val binding : ItemMysquadBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(modelView: MySquadItemModelView?, clickListener: ItemClickListener<MySquadItemModelView>) {
            with(binding) {
                modelView?.let {
                    this.squad = it
                    itemView.setOnClickListener { v -> clickListener.onItemClick(v, it) }
                    this.executePendingBindings()
                }
            }
        }
    }

}