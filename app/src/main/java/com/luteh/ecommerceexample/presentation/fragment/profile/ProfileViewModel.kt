package com.luteh.ecommerceexample.presentation.fragment.profile

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.luteh.ecommerceexample.domain.usecase.MyUseCase

/**
 * Created by Luthfan Maftuh on 9/2/2020.
 * Email : luthfanmaftuh@gmail.com
 */
class ProfileViewModel @ViewModelInject constructor(private val useCase: MyUseCase) :
    ViewModel() {
    val purchasedProducts = useCase.getAllPurchasedProduct().asLiveData()
}