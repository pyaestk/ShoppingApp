package com.example.shoppingapp.domain.repository

import com.example.shoppingapp.domain.model.ItemModel
import com.example.shoppingapp.domain.util.Response
import kotlinx.coroutines.flow.Flow

interface DetailRepository {

    suspend fun addItemToCart(id: Int, quantity: Int, size: String): Response<Unit>

    fun loadItemDetail(itemId: Int): Flow<Response<ItemModel>>
}