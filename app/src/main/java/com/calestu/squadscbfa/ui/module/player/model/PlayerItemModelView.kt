package com.calestu.squadscbfa.ui.module.player.model

import androidx.recyclerview.widget.DiffUtil
import androidx.room.Ignore
import com.calestu.squadscbfa.data.model.type.ClubType
import com.calestu.squadscbfa.data.model.type.PlayerPositionType
import timber.log.Timber

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
                oldItem.entryid == newItem.entryid

            override fun areContentsTheSame(oldItem: PlayerItemModelView, newItem: PlayerItemModelView): Boolean {
                Timber.d("areContentsTheSame.oldItem: $oldItem")
                Timber.d("areContentsTheSame.newItem: $newItem")
                return oldItem == newItem
            }
        }
    }

}