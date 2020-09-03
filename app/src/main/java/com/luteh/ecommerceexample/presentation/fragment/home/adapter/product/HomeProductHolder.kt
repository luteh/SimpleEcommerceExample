package com.luteh.ecommerceexample.presentation.fragment.home.adapter.product

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.luteh.ecommerceexample.domain.model.ProductPromo
import com.luteh.ecommerceexample.utils.ext.toInt
import kotlinx.android.synthetic.main.item_home_product.view.*

/**
 * Created by Luthfan Maftuh on 9/2/2020.
 * Email : luthfanmaftuh@gmail.com
 */
class HomeProductHolder(itemView: View, private val callback: ProductItemCallback) :
    RecyclerView.ViewHolder(itemView) {
    fun bind(productPromo: ProductPromo) {
        with(itemView) {
            productPromo.let {
                Glide.with(this)
                    .load(it.imageUrl)
                    .into(iv_item_product)

                tv_item_product_name.text = it.title
                tv_item_product_price.text = it.price
                iv_item_product_favorite.isSelected = it.loved == 1

                iv_item_product_favorite.setOnClickListener { v ->
                    v.isSelected = !v.isSelected
                    callback.onClickProductFavorite(it, v.isSelected.toInt())
                }

                setOnClickListener { _ ->
                    callback.onClickProductItem(it)
                }
            }
        }
    }
}