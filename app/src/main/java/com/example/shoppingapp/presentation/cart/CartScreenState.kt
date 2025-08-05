package com.example.shoppingapp.presentation.cart

import com.example.shoppingapp.domain.model.CartItemModel

data class CartScreenState(
    val cartItems: List<CartItemModel> = emptyList(),
    val totalPrice: Double = 0.0,
    val isLoading: Boolean = false,
    val error: String? = null
)