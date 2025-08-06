package com.example.shoppingapp.domain.usecase.detail

import com.example.shoppingapp.domain.repository.DetailRepository

class GetItemDetailUseCase(
    private val detailRepository: DetailRepository
){
    suspend operator fun invoke(itemId: Int) = detailRepository.loadItemDetail(itemId)
}