package com.luteh.ecommerceexample.presentation.fragment.home.adapter.product

import com.luteh.ecommerceexample.domain.model.ProductPromo

/**
 * Created by Luthfan Maftuh on 9/2/2020.
 * Email : luthfanmaftuh@gmail.com
 */
interface ProductItemCallback {
    fun onClickProductItem(productPromo: ProductPromo)
    fun onClickProductFavorite(productPromo: ProductPromo, loved: Int)
}