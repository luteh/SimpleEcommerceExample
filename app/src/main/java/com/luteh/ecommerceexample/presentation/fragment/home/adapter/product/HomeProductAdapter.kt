package com.luteh.ecommerceexample.presentation.fragment.home.adapter.product

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.luteh.ecommerceexample.R
import com.luteh.ecommerceexample.domain.model.ProductPromo

/**
 * Created by Luthfan Maftuh on 9/2/2020.
 * Email : luthfanmaftuh@gmail.com
 */
class HomeProductAdapter(private val callback: ProductItemCallback) :
    RecyclerView.Adapter<HomeProductHolder>() {

    private val products = mutableListOf<ProductPromo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeProductHolder =
        HomeProductHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_home_product, parent, false),
            callback
        )

    override fun onBindViewHolder(holder: HomeProductHolder, position: Int) {
        holder.bind(products[position])
    }

    override fun getItemCount(): Int = products.size

    fun setDataSource(data: List<ProductPromo>) {
        products.run {
            clear()
            addAll(data)
        }
        notifyDataSetChanged()
    }
}