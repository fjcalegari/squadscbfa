package com.calestu.squadscbfa.ui.module.player.model

import androidx.recyclerview.widget.DiffUtil
import androidx.room.Ignore
import com.calestu.squadscbfa.data.model.type.ClubType
import com.calestu.squadscbfa.data.model.type.PlayerPositionType

data class PlayerItemModelView(
    val entryid: Int,
    val name: String,
    val club: ClubType,
    val pos: PlayerPositionType,
    val inSquad: Boolean = false
) {

    companion object {

        val diffCallback = object : DiffUtil.ItemCallback<PlayerItemModelView>() {
            override fun areItemsTheSame(oldItem: PlayerItemModelView, newItem: PlayerItemModelView): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: PlayerItemModelView, newItem: PlayerItemModelView): Boolean =
                oldItem == newItem
        }
    }

}