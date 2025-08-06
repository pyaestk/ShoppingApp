package com.example.shoppingapp.presentation.detail.component

import com.example.shoppingapp.domain.model.ItemModel

data class DetailScreenState (
    val itemModel: ItemModel? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
)