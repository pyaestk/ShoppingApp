package com.example.shoppingapp.domain.usecase.home

import com.example.shoppingapp.domain.model.CategoryModel
import com.example.shoppingapp.domain.repository.CategoryRepository
import com.example.shoppingapp.domain.util.Response
import kotlinx.coroutines.flow.Flow

class GetCategoryUseCase(
    private val categoryRepository: CategoryRepository
) {
    operator fun invoke(): Flow<Response<List<CategoryModel>>> {
        return categoryRepository.loadCategories()
    }
}