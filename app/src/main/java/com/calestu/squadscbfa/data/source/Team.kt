package com.calestu.squadscbfa.data.source

import androidx.recyclerview.widget.DiffUtil
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Team(
    var name: String = "",
    var label: String = "",
    var tag: String = ""
) {

    companion object {

        val diffCallback = object : DiffUtil.ItemCallback<Team>() {
            override fun areItemsTheSame(oldItem: Team, newItem: Team): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: Team, newItem: Team): Boolean =
                oldItem == newItem
        }
    }

}