package com.luteh.ecommerceexample.presentation.fragment.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.luteh.ecommerceexample.domain.Resource
import com.luteh.ecommerceexample.domain.model.Home
import com.luteh.ecommerceexample.domain.model.ProductPromo
import com.luteh.ecommerceexample.domain.usecase.MyUseCase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

/**
 * Created by Luthfan Maftuh on 9/1/2020.
 * Email : luthfanmaftuh@gmail.com
 */
class HomeViewModel @ViewModelInject constructor(private val useCase: MyUseCase) : ViewModel() {
//    val homeData = useCase.getHomeData().asLiveData()
    val homeData: MutableLiveData<Resource<List<Home>>> = MutableLiveData()

    fun setFavoriteProduct(productPromo: ProductPromo, loved: Int) {
        useCase.setFavoriteProduct(productPromo, loved)
    }

    fun getHomeData(){
       GlobalScope.launch {
           useCase.getHomeData().collect {
               homeData.postValue(it)
           }
       }
    }
}