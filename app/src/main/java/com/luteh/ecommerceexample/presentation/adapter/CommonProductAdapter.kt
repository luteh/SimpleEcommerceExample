package com.luteh.ecommerceexample.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.luteh.ecommerceexample.R
import com.luteh.ecommerceexample.domain.model.ProductPromo
import java.util.*

/**
 * Created by Luthfan Maftuh on 9/2/2020.
 * Email : luthfanmaftuh@gmail.com
 */
class CommonProductAdapter :
    RecyclerView.Adapter<CommonProductHolder>() {

    private val products = mutableListOf<ProductPromo>()
    private val productDataSources = mutableListOf<ProductPromo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonProductHolder {
        return CommonProductHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_common_product_product, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CommonProductHolder, position: Int) {
        holder.bind(products[position])
    }

    override fun getItemCount(): Int = products.size

    fun setDataSource(data: List<ProductPromo>) {
        products.run {
            clear()
            addAll(data)
        }
        productDataSources.run {
            clear()
            addAll(data)
        }
        notifyDataSetChanged()
    }

    fun filter(query: String) {
        val charText = query.toLowerCase(Locale.getDefault())
        products.clear()
        if (charText.isEmpty()) {
            products.addAll(productDataSources)
        } else {
            for (wp in productDataSources) {
                if (wp.title.toLowerCase(Locale.getDefault()).contains(charText)) {
                    products.add(wp)
                }
            }
        }
        notifyDataSetChanged()
    }
}