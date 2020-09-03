package com.luteh.ecommerceexample.di

import com.luteh.ecommerceexample.domain.usecase.MyUseCase
import com.luteh.ecommerceexample.domain.usecase.MyUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

/**
 * Created by Luthfan Maftuh on 9/1/2020.
 * Email : luthfanmaftuh@gmail.com
 */
@Module
@InstallIn(ActivityComponent::class)
abstract class AppModule {
    @Binds
    abstract fun provideMyUseCase(myUseCaseImpl: MyUseCaseImpl): MyUseCase
}