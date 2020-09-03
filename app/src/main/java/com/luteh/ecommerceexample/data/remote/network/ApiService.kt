package com.luteh.ecommerceexample.data.remote.network

import com.luteh.ecommerceexample.data.remote.response.HomeResponse
import retrofit2.http.GET

/**
 * Created by Luthfan Maftuh on 9/1/2020.
 * Email : luthfanmaftuh@gmail.com
 */
interface ApiService {
    @GET("home")
    suspend fun getHomeData(): List<HomeResponse>
}