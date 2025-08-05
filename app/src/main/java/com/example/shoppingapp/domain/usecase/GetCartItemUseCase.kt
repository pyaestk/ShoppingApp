package com.example.shoppingapp.domain.usecase

import com.example.shoppingapp.domain.repository.CartRepository

class GetCartItemUseCase(
    private val cartRepository: CartRepository
) {
    operator fun invoke() = cartRepository.loadCartItemWithDetails()
}