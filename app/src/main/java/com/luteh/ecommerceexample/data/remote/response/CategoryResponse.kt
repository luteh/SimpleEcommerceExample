package com.luteh.ecommerceexample.data.remote.response


import com.google.gson.annotations.SerializedName
import com.luteh.ecommerceexample.data.local.entity.CategoryEntity

data class CategoryResponse(
    @SerializedName("imageUrl")
    val imageUrl: String, // https://img.icons8.com/bubbles/2x/t-shirt.png
    @SerializedName("id")
    val id: Int, // 21
    @SerializedName("name")
    val name: String // Baju
) {
    companion object {
        fun mapToEntity(categoryResponse: List<CategoryResponse>): List<CategoryEntity> {
            return categoryResponse.map {
                CategoryEntity(
                    imageUrl = it.imageUrl,
                    id = it.id,
                    name = it.name
                )
            }
        }
    }
}