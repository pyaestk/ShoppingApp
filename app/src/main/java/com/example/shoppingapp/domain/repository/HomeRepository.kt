package com.example.shoppingapp.domain.repository

import com.example.shoppingapp.domain.model.BannerModel
import com.example.shoppingapp.domain.model.ItemModel
import com.example.shoppingapp.domain.util.Response
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    fun loadBanners(): Flow<Response<List<BannerModel>>>

    fun loadItems(): Flow<Response<List<ItemModel>>>


}