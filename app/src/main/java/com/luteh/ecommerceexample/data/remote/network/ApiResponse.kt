package com.luteh.ecommerceexample.data.remote.network

/**
 * Created by Luthfan Maftuh on 8/29/2020.
 * Email : luthfanmaftuh@gmail.com
 */
sealed class ApiResponse<out R> {
    data class Success<out T>(val data: T) : ApiResponse<T>()
    data class Error(val errorMessage: String) : ApiResponse<Nothing>()
    object Empty : ApiResponse<Nothing>()
}