package com.example.shoppingapp.presentation.fav

import com.example.shoppingapp.domain.model.ItemModel

data class FavScreenState(
    val isLoading: Boolean = false,
    val items: List<ItemModel> = emptyList(),
    val error: String? = null
)