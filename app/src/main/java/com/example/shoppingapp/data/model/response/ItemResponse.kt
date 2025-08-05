package com.example.shoppingapp.data.model.response


data class ItemResponse(
    val id: Int = 0,
    val categoryId: String = "",
    val description: String = "",
    val picUrl: List<String> = emptyList(),
    val price: Int = 0,
    val rating: Double = 0.0,
    val sellerName: String = "",
    val sellerPic: String = "",
    val sellerTell: Int = 0,
    val showRecommended: Boolean = false,
    val size: List<String> = emptyList(),
    val title: String = ""
)