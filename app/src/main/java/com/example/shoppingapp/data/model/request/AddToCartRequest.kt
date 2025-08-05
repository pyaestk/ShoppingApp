package com.example.shoppingapp.data.model.request

data class AddToCartRequest(
    val itemId: Int = 0,
    val quantity: Int = 0,
    val size: String = "",
)