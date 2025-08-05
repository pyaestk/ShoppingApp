package com.example.shoppingapp.presentation.category

import com.example.shoppingapp.domain.model.CategoryModel
import com.example.shoppingapp.domain.model.ItemModel

data class CategoryScreenState(
    val isLoading: Boolean = false,
    val items: List<ItemModel> = emptyList(),
    val error: String? = null
)