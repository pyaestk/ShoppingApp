package com.example.shoppingapp.domain.usecase.cart

import com.example.shoppingapp.domain.repository.CartRepository
import com.example.shoppingapp.domain.util.Response


class IncreaseCartItemQtyUseCase(
    private val cartRepository: CartRepository
) {
    suspend operator fun invoke(productId: Int): Response<Unit> {
       return cartRepository.incrementCartItemQuantity(productId)
    }
}