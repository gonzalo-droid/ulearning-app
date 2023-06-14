package com.ulearning.ulearning_app.core.functional

sealed class Failure {
    /** When service return 401 or 403 this will force the client to log out of the app.*/
    open class UnauthorizedOrForbidden(val message: String) : Failure()

    open class DefaultError(val idMessage: Int) : Failure()

    /** Weird and strange error that we don´t know the cause.*/
    object None : Failure()

    /** When suddenly the connection is lost.*/
    object NetworkConnectionLostSuddenly : Failure()

    /** When there is no internet network detected.*/
    object NoNetworkDetected : Failure()

    object SSLError : Failure()

    /** When service is taking to long on return the response.*/
    object TimeOut : Failure()

    /** Extend this class for feature specific failures.*/
    open class ServiceUncaughtFailure(val uncaughtFailureMessage: String) : Failure()

    /** Extend this class for feature specific SERVICE ERROR BODY RESPONSE.*/
    open class ServerBodyError(val code: Int, val message: String) : Failure()

    /** Extend this class for feature specific DATA -> DOMAIN MAPPERS exceptions.*/
    open class DataToDomainMapperFailure(val mapperException: String?) : Failure()
}
