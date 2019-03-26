package com.calestu.squadscbfa.ui.module.mysquad.model

import androidx.recyclerview.widget.DiffUtil
import androidx.room.Ignore
import com.calestu.squadscbfa.data.model.type.ClubType
import com.calestu.squadscbfa.data.model.type.PlayerPositionType

data class MySquadModelView(
    val entryid: Int,
    val name: String,
    val club: ClubType,
    val pos: PlayerPositionType
) {

    @Ignore
    var inSquad: Boolean = true

    companion object {

        val diffCallback = object : DiffUtil.ItemCallback<MySquadModelView>() {
            override fun areItemsTheSame(oldItem: MySquadModelView, newItem: MySquadModelView): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: MySquadModelView, newItem: MySquadModelView): Boolean =
                oldItem == newItem
        }
    }

}