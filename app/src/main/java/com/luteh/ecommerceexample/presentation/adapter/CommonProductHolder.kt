package com.luteh.ecommerceexample.presentation.adapter

import android.content.Intent
import android.view.View
import androidx.core.content.ContextCompat.startActivity
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.luteh.ecommerceexample.domain.model.ProductPromo
import com.luteh.ecommerceexample.presentation.activity.detailproduct.DetailProductActivity
import kotlinx.android.synthetic.main.item_common_product_product.view.*

/**
 * Created by Luthfan Maftuh on 9/2/2020.
 * Email : luthfanmaftuh@gmail.com
 */
class CommonProductHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {
    fun bind(productPromo: ProductPromo) {
        with(itemView) {
            productPromo.let {
                Glide.with(this)
                    .load(it.imageUrl)
                    .into(iv_item_common)

                tv_item_common_name.text = it.title
                tv_item_common_price.text = it.price

                setOnClickListener { _ ->
                    val intent = Intent(context, DetailProductActivity::class.java).apply {
                        putExtras(bundleOf(DetailProductActivity.KEY_BUNDLE_PRODUCT to productPromo))
                    }
                    startActivity(context, intent, null)
                }
            }
        }
    }
}