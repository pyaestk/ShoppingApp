package com.example.shoppingapp.data.model.response

data class CartItemResponse(
    val itemId: Int = 0,
    val quantity: Int = 0,
    val size: String = "",
    val title: String = "",
    val price: Int = 0,
    val picUrl: String = ""
)