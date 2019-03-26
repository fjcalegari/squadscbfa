package com.calestu.squadscbfa.data.source.remote.model

import com.google.gson.annotations.SerializedName

data class PlayerRemoteModel (

    @SerializedName("i")
    val entryid: Int,

    @SerializedName("n")
    val name: String,

    @SerializedName("p")
    val pos: Int,

    @SerializedName("s")
    val status: Int

)