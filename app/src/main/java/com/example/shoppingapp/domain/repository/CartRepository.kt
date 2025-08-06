package com.example.shoppingapp.domain.repository

import com.example.shoppingapp.domain.model.CartItemModel
import com.example.shoppingapp.domain.util.Response
import kotlinx.coroutines.flow.Flow

interface CartRepository {

    fun loadCartItemWithDetails(): Flow<Response<List<CartItemModel>>>

    suspend fun incrementCartItemQuantity(itemId: Int): Response<Unit>
    suspend fun decrementCartItemQuantity(itemId: Int): Response<Unit>

    suspend fun deleteCartItem(itemId: Int): Response<Unit>
}