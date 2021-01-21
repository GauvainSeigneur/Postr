package com.seigneur.gauvain.domain.models.providers.request

enum class RequestExceptionType {
    UNKNOWN_HOST,
    ERROR_UNKNOWN,
    CONNECTION_LOST,
    BODY_NULL,
    REQUEST_UNSUCCESSFUL,
    ERROR_RESPONSE,
    UNMAPPED_REQUEST_EXCEPTION,
    HTTP_EXCEPTION
}

const val EXCEPTION_UNKNOWN_HOST_DESC = "Unknown Host Exception"
const val EXCEPTION_CONNECTION_LOST_DESC = "Connection lost during request"
const val EXCEPTION_ERROR_UNKNOWN_DESC = "Error unknown"
const val EXCEPTION_BODY_NULL_DESC = "Body is null"