package com.calestu.squadscbfa.data.source.remote.model

import com.google.gson.annotations.SerializedName

data class AppVersionRemoteModel (

    var entryid: String = "",

    @SerializedName("app_and")
    val app: Int,

    @SerializedName("forceup_and")
    val forceup: Int,

    val round: Int,

    val coach: Int,

    val CAP: Int,
    val CAM: Int,
    val AVA: Int,
    val BAH: Int,
    val BOT: Int,
    val CSA: Int,
    val CEA: Int,
    val CHA: Int,
    val COR: Int,
    val CRU: Int,
    val FLA: Int,
    val FLU: Int,
    val FOR: Int,
    val GOI: Int,
    val GRE: Int,
    val INT: Int,
    val PAL: Int,
    val SAN: Int,
    val SAO: Int,
    val VAS: Int
)