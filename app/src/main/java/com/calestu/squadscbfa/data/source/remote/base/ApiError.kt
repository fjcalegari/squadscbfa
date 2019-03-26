package com.calestu.squadscbfa.data.source.remote.base

import com.calestu.squadscbfa.data.model.InterfaceError
import com.calestu.squadscbfa.data.model.type.GenericErrorType

data class ApiError(
        val httpError:Boolean = false,
        val networkError:Boolean = false,
        val unexpectedError:Boolean = false,
        val errorCode:String? = null,
        val genericErrorType: InterfaceError? = null
) {

    val isHttpError:Boolean
        get() = httpError

    val isNetworkError:Boolean
        get() = httpError

    val isUnexpectedError:Boolean
        get() = httpError

    companion object {

        /** A non-200 HTTP status code was received from the server. */
        @JvmStatic
        fun HTTP(error: BaseErrorResponse.ErrorResponse?): ApiError {
            return error?.code?.let {
                ApiError(httpError = true, errorCode = it)

            } ?: ApiError(unexpectedError = true, genericErrorType = GenericErrorType.ERR_GEN_001)
        }

        /** An {@link IOException} occurred while communicating to the server. */
        @JvmStatic
        fun NETWORK(): ApiError {
            return ApiError(networkError = true, genericErrorType = GenericErrorType.ERR_NETWORK_001)
        }

        /**
         * An internal error occurred while attempting to execute a request. It is best practice to
         * re-throw this exception so your application crashes.
         */
        @JvmStatic
        fun UNEXPECTED(): ApiError {
            return ApiError(unexpectedError = true, genericErrorType = GenericErrorType.ERR_GEN_001)
        }

    }

}