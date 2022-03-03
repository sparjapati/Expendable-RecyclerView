package edu.nitkkr.sanjay.postmanApi.utils

data class Response<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): Response<T> {
            return Response(Status.SUCCESS, data, "Success")
        }

        fun <T> error(message: String?): Response<T> {
            return Response(Status.ERROR, null, message)
        }

        fun <T> loading(): Response<T> {
            return Response(Status.LOADING, null, "Loading")
        }
    }
}