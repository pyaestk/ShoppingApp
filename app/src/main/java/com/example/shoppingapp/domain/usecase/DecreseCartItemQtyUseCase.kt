package com.example.shoppingapp.domain.usecase

import com.example.shoppingapp.domain.repository.CartRepository
import com.example.shoppingapp.domain.util.Response

class DecreaseCartItemQtyUseCase(
    private val cartRepository: CartRepository
) {
    suspend operator fun invoke(productId: Int): Response<Unit> {
        return cartRepository.decrementCartItemQuantity(productId)
    }
}