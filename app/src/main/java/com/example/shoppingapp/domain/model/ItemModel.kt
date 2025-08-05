package com.example.shoppingapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemModel(
    val id: Int,
    val categoryId: String,
    val description: String,
    val picUrl: List<String>,
    val price: Int,
    val rating: Double,
    val sellerName: String,
    val sellerPic: String,
    val sellerTell: Int,
    val showRecommended: Boolean,
    val size: List<String>,
    val title: String
): Parcelable