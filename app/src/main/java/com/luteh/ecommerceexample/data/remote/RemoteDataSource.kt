package com.luteh.ecommerceexample.data.remote

import com.luteh.ecommerceexample.data.remote.network.ApiResponse
import com.luteh.ecommerceexample.data.remote.network.ApiService
import com.luteh.ecommerceexample.data.remote.response.HomeResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Luthfan Maftuh on 9/1/2020.
 * Email : luthfanmaftuh@gmail.com
 */
@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {
    suspend fun getHomeData(): Flow<ApiResponse<List<HomeResponse>>> = flow {
        try {
            val response = apiService.getHomeData()
            if (response.isNotEmpty()) {
                emit(ApiResponse.Success(response))
            } else {
                emit(ApiResponse.Empty)
            }
        } catch (e: Exception) {
            emit(ApiResponse.Error(e.toString()))
            Timber.e(e.toString())
        }
    }.flowOn(Dispatchers.IO)

}