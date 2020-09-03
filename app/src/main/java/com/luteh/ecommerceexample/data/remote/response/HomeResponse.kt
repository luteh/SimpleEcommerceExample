package com.luteh.ecommerceexample.data.remote.response


import com.google.gson.annotations.SerializedName
import com.luteh.ecommerceexample.domain.model.Category
import com.luteh.ecommerceexample.domain.model.Data
import com.luteh.ecommerceexample.domain.model.Home
import com.luteh.ecommerceexample.domain.model.ProductPromo

data class HomeResponse(
    @SerializedName("data")
    val `data`: DataResponse
) {
    companion object {
        fun mapResponseToDomain(homeResponse: HomeResponse): Home = Home(
            data = Data(
                category = homeResponse.data.category.map {
                    Category(
                        imageUrl = it.imageUrl,
                        id = it.id,
                        name = it.name
                    )
                },
                productPromo = homeResponse.data.productPromo.map {
                    ProductPromo(
                        id = it.id,
                        imageUrl = it.imageUrl,
                        title = it.title,
                        description = it.description,
                        price = it.price,
                        loved = it.loved
                    )
                }
            )
        )
    }
}