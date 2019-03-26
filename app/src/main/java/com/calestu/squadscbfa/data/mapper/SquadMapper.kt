package com.calestu.squadscbfa.data.mapper

import com.calestu.squadscbfa.data.entity.SquadEntity
import com.calestu.squadscbfa.ui.module.mysquad.model.MySquadItemModelView
import com.calestu.squadscbfa.util.ext.formatToViewDateTimeDefaults
import java.util.*

fun SquadEntity.toMySquadItemModelView() : MySquadItemModelView {
    return MySquadItemModelView(
        entryid = entryid,
        updatedTime = Date(updatedTime).formatToViewDateTimeDefaults(),
        title = title,
        formation = formation,
        round = round
    )
}