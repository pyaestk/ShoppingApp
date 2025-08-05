package com.example.shoppingapp.domain.repository

import com.example.shoppingapp.domain.model.CategoryModel
import com.example.shoppingapp.domain.model.ItemModel
import com.example.shoppingapp.domain.util.Response
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    fun loadCategories(): Flow<Response<List<CategoryModel>>>

    fun loadItemByCategory(categoryId: Int): Flow<Response<List<ItemModel>>>
}