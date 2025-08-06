package com.example.shoppingapp.domain.usecase.cart

import com.example.shoppingapp.domain.repository.CartRepository
import com.example.shoppingapp.domain.util.Response

class RemoveCartItemUseCase(
    private val cartRepository: CartRepository
) {
    suspend operator fun invoke(itemId: Int): Response<Unit> {
        return cartRepository.deleteCartItem(itemId)
    }
}