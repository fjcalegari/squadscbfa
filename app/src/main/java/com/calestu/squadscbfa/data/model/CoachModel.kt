package com.calestu.squadscbfa.data.model

import com.calestu.squadscbfa.data.model.type.ClubType

data class CoachModel (
    val entryid: Int,
    val club: ClubType,
    val name: String
)