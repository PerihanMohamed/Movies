package com.example.mymoviedatabase.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class ServiceResponse<T>(
    @SerializedName("data") val data: T
)
data class Resource<out T>(val status: Status,val data: T?,val message: String?) {

    companion object{
        fun<T> success(data:T):Resource<T> = Resource(status= Status.SUCCESS,data = data,message = null)
        fun<T> error(data:T?,message: String?):Resource<T> = Resource(status= Status.ERROR,data = data,message = message)
        fun<T> loading(data:T?):Resource<T> = Resource(status= Status.LOADING,data = data,message = null)
    }


}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}
//data class Result<out T>(val status: Status, val data: T?, val error: Error?, val message: String?) {
//
//    enum class Status {
//        SUCCESS,
//        ERROR,
//        LOADING
//    }
//
//    companion object {
//        fun <T> success(data: T?): Result<T> {
//            return Result(Status.SUCCESS, data, null, null)
//        }
//
//        fun <T> error(message: String, error: Error?): Result<T> {
//            return Result(Status.ERROR, null, error, message)
//        }
//
//        fun <T> loading(data: T? = null): Result<T> {
//            return Result(Status.LOADING, data, null, null)
//        }
//    }
//
//    override fun toString(): String {
//        return "Result(status=$status, data=$data, error=$error, message=$message)"
//    }
//}