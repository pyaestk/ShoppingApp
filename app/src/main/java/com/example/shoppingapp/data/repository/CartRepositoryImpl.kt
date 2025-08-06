package com.example.shoppingapp.data.repository

import com.example.shoppingapp.data.remote.CartRemoteDataSource
import com.example.shoppingapp.domain.model.CartItemModel
import com.example.shoppingapp.domain.repository.CartRepository
import com.example.shoppingapp.domain.util.Response
import kotlinx.coroutines.flow.Flow

class CartRepositoryImpl(
    private val itemRemoteDataSource: CartRemoteDataSource
): CartRepository {

    override fun loadCartItemWithDetails(): Flow<Response<List<CartItemModel>>> {
        return itemRemoteDataSource.loadCartItemWithDetails()
    }

    override suspend fun incrementCartItemQuantity(itemId: Int): Response<Unit> {
        return itemRemoteDataSource.incrementCartItemQuantity(itemId)
    }

    override suspend fun decrementCartItemQuantity(itemId: Int): Response<Unit> {
        return itemRemoteDataSource.decrementCartItemQuantity(itemId)
    }

    override suspend fun deleteCartItem(itemId: Int): Response<Unit> {
        return itemRemoteDataSource.deleteCartItem(itemId)
    }
}