package com.luteh.ecommerceexample.presentation.activity.favorite

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.luteh.ecommerceexample.domain.usecase.MyUseCase

/**
 * Created by Luthfan Maftuh on 9/3/2020.
 * Email : luthfanmaftuh@gmail.com
 */
class FavoriteViewModel @ViewModelInject constructor(private val useCase: MyUseCase):ViewModel() {
    val favorites = useCase.getFavoriteProductPromo().asLiveData()
}