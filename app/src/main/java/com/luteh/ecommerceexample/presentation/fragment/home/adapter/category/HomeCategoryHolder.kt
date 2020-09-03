package com.luteh.ecommerceexample.presentation.fragment.home.adapter.category

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.luteh.ecommerceexample.domain.model.Category
import kotlinx.android.synthetic.main.item_home_category.view.*

/**
 * Created by Luthfan Maftuh on 9/2/2020.
 * Email : luthfanmaftuh@gmail.com
 */
class HomeCategoryHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(category: Category) {
        with(itemView) {
            category.let {
                Glide.with(this)
                    .load(it.imageUrl)
                    .into(iv_item_category)

                tv_item_category.text = it.name
            }
        }
    }
}