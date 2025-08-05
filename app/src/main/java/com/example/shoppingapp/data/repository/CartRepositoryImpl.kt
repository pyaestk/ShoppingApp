package com.example.shoppingapp.data.repository

import com.example.shoppingapp.data.remote.ItemRemoteDataSource
import com.example.shoppingapp.domain.model.CartItemModel
import com.example.shoppingapp.domain.repository.CartRepository
import com.example.shoppingapp.domain.util.Response
import kotlinx.coroutines.flow.Flow

class CartRepositoryImpl(
    private val itemRemoteDataSource: ItemRemoteDataSource
): CartRepository {
    override suspend fun addItemToCart(
        id: Int,
        quantity: Int,
        size: String
    ): Response<Unit> {
        return itemRemoteDataSource.addItemToCart(id, quantity, size)
    }

    override fun loadCartItemWithDetails(): Flow<Response<List<CartItemModel>>> {
        return itemRemoteDataSource.loadCartItemWithDetails()
    }

    override suspend fun incrementCartItemQuantity(itemId: Int): Response<Unit> {
        return itemRemoteDataSource.incrementCartItemQuantity(itemId)
    }

    override suspend fun decrementCartItemQuantity(itemId: Int): Response<Unit> {
        return itemRemoteDataSource.decrementCartItemQuantity(itemId)
    }
}