package com.calestu.squadscbfa.data.source.remote.base

data class BaseErrorResponse(val error: ErrorResponse?) {

    data class ErrorResponse(
            val code: String? = null,
            val message:String? = null
    )

}