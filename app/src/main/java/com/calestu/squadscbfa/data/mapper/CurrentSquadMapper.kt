package com.calestu.squadscbfa.data.mapper

import com.calestu.squadscbfa.data.entity.CurrentSquadEntity
import com.calestu.squadscbfa.ui.module.squad.add.model.SquadAddModel

fun CurrentSquadEntity.fromEntity() : SquadAddModel {
    return SquadAddModel(
        entryid = entryid,
        title = title,
        round = round,
        formation = formation
    )
}