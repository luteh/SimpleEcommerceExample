package com.luteh.ecommerceexample.data.local

import com.luteh.ecommerceexample.data.local.entity.CategoryEntity
import com.luteh.ecommerceexample.data.local.entity.ProductPromoEntity
import com.luteh.ecommerceexample.data.local.entity.PurchasedProductEntity
import com.luteh.ecommerceexample.data.local.room.MyDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Luthfan Maftuh on 9/1/2020.
 * Email : luthfanmaftuh@gmail.com
 */
@Singleton
class LocalDataSource @Inject constructor(private val myDao: MyDao) {
    suspend fun insertCategoriesAndProducts(
        categories: List<CategoryEntity>,
        productPromoList: List<ProductPromoEntity>
    ) {
        myDao.insertCategoriesAndProducts(categories, productPromoList)
    }

    suspend fun insertPurchasedProduct(
        productPromoEntity: ProductPromoEntity,
        purchasedDate: Long
    ) {
        myDao.insertPurchasedProduct(productPromoEntity.let {
            PurchasedProductEntity(
                id = it.id,
                imageUrl = it.imageUrl,
                title = it.title,
                description = it.description,
                price = it.price,
                loved = it.loved,
                purchasedDate = purchasedDate
            )
        })
    }

    fun getAllCategory(): Flow<List<CategoryEntity>> = myDao.getAllCategory()
    fun getAllProductPromo(): Flow<List<ProductPromoEntity>> = myDao.getAllProductPromo()
    fun getAllPurchasedProduct(): Flow<List<PurchasedProductEntity>> =
        myDao.getAllPurchasedProduct()

    fun getFavoriteProductPromo(): Flow<List<ProductPromoEntity>> = myDao.getFavoriteProductPromo()
    fun getProductPromoById(id: String): Flow<ProductPromoEntity> = myDao.getProductPromoById(id)

    fun setFavoriteTourism(productPromoEntity: ProductPromoEntity, loved: Int) {
        productPromoEntity.loved = loved
        myDao.updateFavoriteProductPromo(productPromoEntity)
    }
}