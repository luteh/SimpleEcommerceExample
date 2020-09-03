package com.luteh.ecommerceexample.domain.repository

import com.luteh.ecommerceexample.domain.Resource
import com.luteh.ecommerceexample.domain.model.Home
import com.luteh.ecommerceexample.domain.model.ProductPromo
import kotlinx.coroutines.flow.Flow

/**
 * Created by Luthfan Maftuh on 9/1/2020.
 * Email : luthfanmaftuh@gmail.com
 */
interface IMyRepository {
    fun getHomeData(): Flow<Resource<List<Home>>>

    fun getFavoriteProductPromo(): Flow<List<ProductPromo>>

    fun setFavoriteTourism(productPromo: ProductPromo, loved: Int)
    fun getProductPromoById(id: String): Flow<ProductPromo>

    fun insertPurchasedProduct(
        productPromo: ProductPromo,
        purchasedDate: Long
    )

    fun getAllPurchasedProduct(): Flow<List<ProductPromo>>
}