package com.luteh.ecommerceexample.data.di

import com.luteh.ecommerceexample.data.MyRepositoryImpl
import com.luteh.ecommerceexample.domain.repository.IMyRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

/**
 * Created by Luthfan Maftuh on 9/1/2020.
 * Email : luthfanmaftuh@gmail.com
 */
@Module(includes = [NetworkModule::class])
@InstallIn(ApplicationComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideRepository(myRepositoryImpl: MyRepositoryImpl): IMyRepository
}