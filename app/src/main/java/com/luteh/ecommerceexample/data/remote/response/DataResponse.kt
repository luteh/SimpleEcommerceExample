package com.luteh.ecommerceexample.data.remote.response


import com.google.gson.annotations.SerializedName

data class DataResponse(
    @SerializedName("category")
    val category: List<CategoryResponse>,
    @SerializedName("productPromo")
    val productPromo: List<ProductPromoResponse>
)