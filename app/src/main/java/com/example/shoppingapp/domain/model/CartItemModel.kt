package com.example.shoppingapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CartItemModel(
    val itemId: Int,
    val quantity: Int,
    val size: String,
    val title: String,
    val price: Int,
    val picUrl: String
): Parcelable