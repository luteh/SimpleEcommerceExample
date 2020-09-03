package com.luteh.ecommerceexample.data

import com.luteh.ecommerceexample.data.local.LocalDataSource
import com.luteh.ecommerceexample.data.local.entity.CategoryEntity
import com.luteh.ecommerceexample.data.local.entity.ProductPromoEntity
import com.luteh.ecommerceexample.data.local.entity.PurchasedProductEntity
import com.luteh.ecommerceexample.data.remote.RemoteDataSource
import com.luteh.ecommerceexample.data.remote.network.ApiResponse
import com.luteh.ecommerceexample.data.remote.response.CategoryResponse
import com.luteh.ecommerceexample.data.remote.response.HomeResponse
import com.luteh.ecommerceexample.data.remote.response.ProductPromoResponse
import com.luteh.ecommerceexample.domain.Resource
import com.luteh.ecommerceexample.domain.model.Data
import com.luteh.ecommerceexample.domain.model.Home
import com.luteh.ecommerceexample.domain.model.ProductPromo
import com.luteh.ecommerceexample.domain.repository.IMyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Luthfan Maftuh on 9/1/2020.
 * Email : luthfanmaftuh@gmail.com
 */
@Singleton
class MyRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMyRepository {
    override fun getHomeData(): Flow<Resource<List<Home>>> =
        object : NetworkBoundResource<List<Home>, List<HomeResponse>>() {
            override fun loadFromDB(): Flow<List<Home>> {
                return flow<List<Home>> {
                    localDataSource.getAllCategory()
                        .combine(localDataSource.getAllProductPromo()) { a, b ->
                            mutableListOf<Home>().apply {
                                add(
                                    Home(
                                        data = Data(
                                            category = CategoryEntity.mapToDomain(a),
                                            productPromo = ProductPromoEntity.mapToDomain(b)
                                        )
                                    )
                                )
                            }
                        }.collect {
                            emit(it)
                        }
                }.flowOn(Dispatchers.IO)
            }

            override fun shouldFetch(data: List<Home>?): Boolean {
                return data?.get(0)?.data?.category.isNullOrEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<HomeResponse>>> {
                return remoteDataSource.getHomeData()
            }

            override suspend fun saveCallResult(data: List<HomeResponse>) {
                data[0].data.let {
                    localDataSource.insertCategoriesAndProducts(
                        CategoryResponse.mapToEntity(it.category),
                        ProductPromoResponse.mapToEntity(it.productPromo)
                    )
                }
            }
        }.asFlow()

    override fun getFavoriteProductPromo(): Flow<List<ProductPromo>> {
        return localDataSource.getFavoriteProductPromo().map {
            ProductPromoEntity.mapToDomain(it)
        }
    }

    override fun getProductPromoById(id: String): Flow<ProductPromo> {
        return localDataSource.getProductPromoById(id).map {
            ProductPromoEntity.mapToDomain(it)
        }
    }

    override fun setFavoriteTourism(productPromo: ProductPromo, loved: Int) {
        val productPromoEntity = ProductPromo.mapToEntity(productPromo)
        appExecutors.diskIO()
            .execute { localDataSource.setFavoriteTourism(productPromoEntity, loved) }
    }

    override fun insertPurchasedProduct(productPromo: ProductPromo, purchasedDate: Long) {
        GlobalScope.launch {
            localDataSource.insertPurchasedProduct(
                productPromoEntity = ProductPromo.mapToEntity(
                    productPromo
                ),
                purchasedDate = purchasedDate
            )
        }
    }

    override fun getAllPurchasedProduct(): Flow<List<ProductPromo>> {
        return localDataSource.getAllPurchasedProduct().map {
            PurchasedProductEntity.mapToDomains(it)
        }
    }
}

