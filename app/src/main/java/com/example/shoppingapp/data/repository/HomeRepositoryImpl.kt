package com.example.shoppingapp.data.repository

import com.example.shoppingapp.data.remote.BannerRemoteDataSource
import com.example.shoppingapp.data.remote.ItemRemoteDataSource
import com.example.shoppingapp.domain.model.BannerModel
import com.example.shoppingapp.domain.model.ItemModel
import com.example.shoppingapp.domain.repository.HomeRepository
import com.example.shoppingapp.domain.util.Response
import kotlinx.coroutines.flow.Flow

class HomeRepositoryImpl(
    private val itemRemoteDataSource: ItemRemoteDataSource,
    private val bannerRemoteDataSource: BannerRemoteDataSource
) : HomeRepository {
    override fun loadBanners(): Flow<Response<List<BannerModel>>> {
        return bannerRemoteDataSource.loadBanners()
    }

    override fun loadItems(): Flow<Response<List<ItemModel>>> {
        return itemRemoteDataSource.loadItems()
    }
}

