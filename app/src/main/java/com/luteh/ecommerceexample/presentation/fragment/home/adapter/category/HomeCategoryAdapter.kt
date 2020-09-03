package com.luteh.ecommerceexample.presentation.fragment.home.adapter.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.luteh.ecommerceexample.R
import com.luteh.ecommerceexample.domain.model.Category

/**
 * Created by Luthfan Maftuh on 9/2/2020.
 * Email : luthfanmaftuh@gmail.com
 */
class HomeCategoryAdapter : RecyclerView.Adapter<HomeCategoryHolder>() {

    private val categories = mutableListOf<Category>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeCategoryHolder =
        HomeCategoryHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_home_category, parent, false)
        )

    override fun onBindViewHolder(holder: HomeCategoryHolder, position: Int) {
        holder.bind(categories[position])
    }

    override fun getItemCount(): Int = categories.size

    fun setDataSource(data: List<Category>) {
        categories.run {
            clear()
            addAll(data)
        }
        notifyDataSetChanged()
    }
}