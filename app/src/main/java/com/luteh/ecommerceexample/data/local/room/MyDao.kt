package com.luteh.ecommerceexample.data.local.room

import androidx.room.*
import com.luteh.ecommerceexample.data.local.entity.CategoryEntity
import com.luteh.ecommerceexample.data.local.entity.ProductPromoEntity
import com.luteh.ecommerceexample.data.local.entity.PurchasedProductEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created by Luthfan Maftuh on 9/1/2020.
 * Email : luthfanmaftuh@gmail.com
 */
@Dao
interface MyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategoriesAndProducts(
        categories: List<CategoryEntity>,
        productPromoList: List<ProductPromoEntity>
    )

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPurchasedProduct(purchasedProductEntity: PurchasedProductEntity)

    @Query("SELECT * FROM category")
    fun getAllCategory(): Flow<List<CategoryEntity>>

    @Query("SELECT * FROM product_promo")
    fun getAllProductPromo(): Flow<List<ProductPromoEntity>>

    @Query("SELECT * FROM purchased_product ORDER BY purchased_date DESC")
    fun getAllPurchasedProduct(): Flow<List<PurchasedProductEntity>>

    @Query("SELECT * FROM product_promo where loved = 1")
    fun getFavoriteProductPromo(): Flow<List<ProductPromoEntity>>

    @Query("SELECT * FROM product_promo where id = :id")
    fun getProductPromoById(id: String): Flow<ProductPromoEntity>

    @Update
    fun updateFavoriteProductPromo(productPromo: ProductPromoEntity)
}