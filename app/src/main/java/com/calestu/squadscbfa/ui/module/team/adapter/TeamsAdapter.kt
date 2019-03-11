package com.calestu.squadscbfa.ui.module.team.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.calestu.squadscbfa.data.source.Team
import com.calestu.squadscbfa.databinding.ItemTeamBinding
import timber.log.Timber

class TeamsAdapter : ListAdapter<Team, TeamsAdapter.ItemHolder>(Team.diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        Timber.d("onCreateViewHolder: ")
        return ItemHolder(ItemTeamBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        Timber.d("onBindViewHolder: ")
        holder.bind(getItemModel(position))
    }

    fun getItemModel(position: Int): Team? {
        return super.getItem(position)
    }

    class ItemHolder(val binding : ItemTeamBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(modelView: Team?) {
            Timber.d("bind: ")
            with(binding) {
                team = modelView
                executePendingBindings()
            }
        }
    }

}