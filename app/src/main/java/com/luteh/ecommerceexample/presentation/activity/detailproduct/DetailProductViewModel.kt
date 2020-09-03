package com.luteh.ecommerceexample.presentation.activity.detailproduct

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.luteh.ecommerceexample.domain.model.ProductPromo
import com.luteh.ecommerceexample.domain.usecase.MyUseCase

/**
 * Created by Luthfan Maftuh on 9/2/2020.
 * Email : luthfanmaftuh@gmail.com
 */
class DetailProductViewModel @ViewModelInject constructor(private val useCase: MyUseCase) :
    ViewModel() {
    private val getProductByIdLiveData = MutableLiveData<String>()
    val productLiveData: LiveData<ProductPromo> =
        getProductByIdLiveData.switchMap { useCase.getProductPromoById(it).asLiveData() }

    fun setFavoriteProduct(productPromo: ProductPromo, loved: Int) {
        useCase.setFavoriteProduct(productPromo, loved)
    }

    fun insertPurchasedProduct(productPromo: ProductPromo, purchasedDate: Long) {
        useCase.insertPurchasedProduct(productPromo, purchasedDate)
    }

    fun getProductPromoById(id: String) {
        getProductByIdLiveData.value = id
    }
}