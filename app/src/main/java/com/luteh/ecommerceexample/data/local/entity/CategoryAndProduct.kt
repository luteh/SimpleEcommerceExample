package com.luteh.ecommerceexample.data.local.entity

import androidx.room.Embedded

/**
 * Created by Luthfan Maftuh on 9/1/2020.
 * Email : luthfanmaftuh@gmail.com
 */
data class CategoryAndProduct(
    @Embedded
    val categoryEntity: CategoryEntity,
    @Embedded(prefix = "product_")
    val productPromoEntity: ProductPromoEntity
)