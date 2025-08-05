package com.example.shoppingapp.domain.usecase

import com.example.shoppingapp.domain.repository.CartRepository
import com.example.shoppingapp.domain.util.Response

class AddItemToCartUseCase(
    private val cartRepository: CartRepository

) {

    suspend operator fun invoke(id: Int, quantity: Int, size: String): Response<Unit> {
        return cartRepository.addItemToCart(id, quantity, size)
    }
}