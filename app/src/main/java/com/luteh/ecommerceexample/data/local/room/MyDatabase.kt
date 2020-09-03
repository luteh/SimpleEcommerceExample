package com.luteh.ecommerceexample.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.luteh.ecommerceexample.data.local.entity.CategoryEntity
import com.luteh.ecommerceexample.data.local.entity.ProductPromoEntity
import com.luteh.ecommerceexample.data.local.entity.PurchasedProductEntity

/**
 * Created by Luthfan Maftuh on 9/1/2020.
 * Email : luthfanmaftuh@gmail.com
 */
@Database(
    entities = [CategoryEntity::class, ProductPromoEntity::class, PurchasedProductEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MyDatabase : RoomDatabase() {
    abstract fun myDao(): MyDao
}