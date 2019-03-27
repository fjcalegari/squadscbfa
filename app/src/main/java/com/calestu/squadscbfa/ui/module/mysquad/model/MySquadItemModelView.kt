package com.calestu.squadscbfa.ui.module.mysquad.model

import androidx.recyclerview.widget.DiffUtil
import com.calestu.squadscbfa.data.model.type.FormationType
import com.calestu.squadscbfa.data.model.type.RoundType

data class MySquadItemModelView(
    val entryid: String,
    val updatedTime: String,
    val title: String,
    val formation: FormationType,
    val round: RoundType
) {

    companion object {

        val diffCallback = object : DiffUtil.ItemCallback<MySquadItemModelView>() {
            override fun areItemsTheSame(oldItem: MySquadItemModelView, newItem: MySquadItemModelView): Boolean =
                oldItem.entryid == newItem.entryid

            override fun areContentsTheSame(oldItem: MySquadItemModelView, newItem: MySquadItemModelView): Boolean =
                oldItem == newItem
        }
    }

}