package com.luteh.ecommerceexample.data.di

import android.content.Context
import androidx.room.Room
import com.luteh.ecommerceexample.data.local.room.MyDao
import com.luteh.ecommerceexample.data.local.room.MyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

/**
 * Created by Luthfan Maftuh on 9/1/2020.
 * Email : luthfanmaftuh@gmail.com
 */
@Module
@InstallIn(ApplicationComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): MyDatabase = Room.databaseBuilder(
        context,
        MyDatabase::class.java, "my_database.db"
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideMyDao(database: MyDatabase): MyDao = database.myDao()
}