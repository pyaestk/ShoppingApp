package com.example.shoppingapp.domain.usecase.category

import com.example.shoppingapp.domain.repository.CategoryRepository

class GetItemsByCategoryUseCase(
    private val categoryRepository: CategoryRepository
) {
    operator fun invoke(categoryId: Int) = categoryRepository.loadItemByCategory(categoryId)
}