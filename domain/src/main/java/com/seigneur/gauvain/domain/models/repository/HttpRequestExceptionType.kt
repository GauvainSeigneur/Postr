package com.seigneur.gauvain.domain.models.repository

/**
 * Remote request exception type
 */
enum class HttpRequestExceptionType {
    //40X errors
    BAD_REQUEST,
    UNAUTHORIZED,
    FORBIDDEN,
    NOT_FOUND,
    //50X error
    SERVER_ERROR,
    //Other
    UNKNOWN_HOST,
    CONNECTION_LOST,
    BODY_NULL,
    UNMAPPED_REQUEST_EXCEPTION,
    ERROR_UNKNOWN
}

const val EXCEPTION_UNKNOWN_HOST_DESC = "Unknown Host Exception"
const val EXCEPTION_CONNECTION_LOST_DESC = "Connection lost during request"
const val EXCEPTION_ERROR_UNKNOWN_DESC = "Error unknown"
const val EXCEPTION_BODY_NULL_DESC = "Body is null"