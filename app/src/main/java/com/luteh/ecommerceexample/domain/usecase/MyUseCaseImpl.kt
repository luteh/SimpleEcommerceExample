package com.luteh.ecommerceexample.domain.usecase

import com.luteh.ecommerceexample.domain.Resource
import com.luteh.ecommerceexample.domain.model.Home
import com.luteh.ecommerceexample.domain.model.ProductPromo
import com.luteh.ecommerceexample.domain.repository.IMyRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Luthfan Maftuh on 9/1/2020.
 * Email : luthfanmaftuh@gmail.com
 */
class MyUseCaseImpl @Inject constructor(private val repository: IMyRepository) : MyUseCase {
    override fun getHomeData(): Flow<Resource<List<Home>>> = repository.getHomeData()
    override fun getFavoriteProductPromo(): Flow<List<ProductPromo>> =
        repository.getFavoriteProductPromo()

    override fun setFavoriteProduct(productPromo: ProductPromo, loved: Int) =
        repository.setFavoriteTourism(productPromo, loved)

    override fun getProductPromoById(id: String): Flow<ProductPromo> =
        repository.getProductPromoById(id)

    override fun insertPurchasedProduct(productPromo: ProductPromo, purchasedDate: Long) {
        repository.insertPurchasedProduct(productPromo, purchasedDate)
    }

    override fun getAllPurchasedProduct(): Flow<List<ProductPromo>> =
        repository.getAllPurchasedProduct()
}