package com.calestu.squadscbfa.ui.module.player.model

import androidx.recyclerview.widget.DiffUtil
import androidx.room.Ignore
import com.calestu.squadscbfa.data.model.type.ClubType
import com.calestu.squadscbfa.data.model.type.PlayerPositionType

data class PlayerModelView(
    val entryid: Int,
    val name: String,
    val club: ClubType,
    val pos: PlayerPositionType
) {

    @Ignore
    var inSquad: Boolean = true

    companion object {

        val diffCallback = object : DiffUtil.ItemCallback<PlayerModelView>() {
            override fun areItemsTheSame(oldItem: PlayerModelView, newItem: PlayerModelView): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: PlayerModelView, newItem: PlayerModelView): Boolean =
                oldItem == newItem
        }
    }

}