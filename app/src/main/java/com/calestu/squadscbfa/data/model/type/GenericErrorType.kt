package com.calestu.squadscbfa.data.model.type

import com.calestu.squadscbfa.R
import com.calestu.squadscbfa.data.model.InterfaceError

enum class GenericErrorType: InterfaceError {

    ERR_GEN_001{
        override fun message() = R.string.general_error_title
    },
    ERR_NETWORK_001{
        override fun message() = R.string.internet_connection_not_found
    }
    ;

    companion object {

        @JvmStatic
        fun from(errorCode: String): GenericErrorType = GenericErrorType.values().firstOrNull {
            it.name == errorCode
        } ?: ERR_GEN_001

    }

}