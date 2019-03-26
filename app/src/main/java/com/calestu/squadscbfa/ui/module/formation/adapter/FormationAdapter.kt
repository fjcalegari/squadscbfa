package com.calestu.squadscbfa.ui.module.formation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.calestu.squadscbfa.R
import com.calestu.squadscbfa.data.model.type.FormationType
import com.calestu.squadscbfa.databinding.ItemFormationBinding
import com.calestu.squadscbfa.ui.base.ItemClickListener
import com.calestu.squadscbfa.util.ext.color
import timber.log.Timber

class FormationAdapter (
    private val itemClickListener: ItemClickListener<FormationType>
) : RecyclerView.Adapter<FormationAdapter.ListViewHolder>() {

    private var list: List<FormationType> = listOf()

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.item_formation, parent, false))
    }

    fun setFormationsList(formationsList: List<FormationType>) {
        this.list = formationsList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ListViewHolder(val binding: ItemFormationBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(formationType: FormationType) {
            binding.formation = formationType

            if (formationType.selected) {
                binding.selectedButton.isChecked = true
                binding.tvTitle.setTextColor(binding.tvTitle.context.color(R.color.colorAccent))
//            } else {
//                binding.selectedButton.isChecked = false
//                binding.tvTitle.setTextColor(binding.tvTitle.context.color(R.color.gray_dark))
            }

            binding.selectedButton.setOnClickListener { v -> itemClickListener.onItemClick(v, formationType) }
            itemView.setOnClickListener { v -> itemClickListener.onItemClick(v, formationType) }
            binding.executePendingBindings()
        }

    }

}