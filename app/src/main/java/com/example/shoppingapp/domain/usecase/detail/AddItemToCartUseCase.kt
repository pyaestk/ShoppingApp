package com.example.shoppingapp.domain.usecase.detail

import com.example.shoppingapp.domain.repository.DetailRepository
import com.example.shoppingapp.domain.util.Response

class AddItemToCartUseCase(
    private val detailRepository: DetailRepository

) {

    suspend operator fun invoke(id: Int, quantity: Int, size: String): Response<Unit> {
        return detailRepository.addItemToCart(id, quantity, size)
    }
}