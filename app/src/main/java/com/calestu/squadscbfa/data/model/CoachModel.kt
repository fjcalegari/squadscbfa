package com.calestu.squadscbfa.data.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class  CoachModel(
    var entryid: Int = 0,
    var club: Int = 0,
    var name: String = ""
)