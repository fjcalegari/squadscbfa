package com.calestu.squadscbfa.data.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class AppInfoModel(
    var entryid: String = "",
    var firstOpenTime: Long = 0L,
    var app: Int = 0,
    var forceup: Int = 0,
    var round: Int = 0,
    var players: Int = 0,
    var coaches: Int = 0,
    var firstOpen : Boolean = false,
    var syncClubs : Boolean = false,
    var syncPlayers : Boolean = false,
    var syncCoaches : Boolean = false,
    var emptyResult : Boolean = false
)