package com.calestu.squadscbfa.ui.module.player.model

import androidx.recyclerview.widget.DiffUtil
import com.calestu.squadscbfa.data.model.type.ClubType
import com.calestu.squadscbfa.data.model.type.FormationType
import com.calestu.squadscbfa.data.model.type.PlayerPositionType
import com.calestu.squadscbfa.data.model.type.RoundType

data class PlayerItemModelView(
    val entryid: Int,
    val club: ClubType,
    val name: String,
    val position: PlayerPositionType,
    var status: PlayerItemModelViewStatus
) {

    val showSelected: Boolean
        get() = status == PlayerItemModelViewStatus.SELECTED

    val showNotSelected: Boolean
        get() = status == PlayerItemModelViewStatus.NOT_SELECTED

    val showDisable: Boolean
        get() = status == PlayerItemModelViewStatus.DISABLE

    companion object {

        val diffCallback = object : DiffUtil.ItemCallback<PlayerItemModelView>() {
            override fun areItemsTheSame(oldItem: PlayerItemModelView, newItem: PlayerItemModelView): Boolean =
                oldItem.entryid == newItem.entryid

            override fun areContentsTheSame(oldItem: PlayerItemModelView, newItem: PlayerItemModelView): Boolean =
                oldItem.status == newItem.status
        }
    }

}

enum class PlayerItemModelViewStatus {
    SELECTED,
    NOT_SELECTED,
    DISABLE
}