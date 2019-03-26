package com.calestu.squadscbfa.data.source.remote.model

import com.google.gson.annotations.SerializedName

data class CoachRemoteModel (

    @SerializedName("i")
    val entryid: Int,

    @SerializedName("c")
    val club: Int,

    @SerializedName("n")
    val name: String,

    @SerializedName("s")
    val status: Int

)