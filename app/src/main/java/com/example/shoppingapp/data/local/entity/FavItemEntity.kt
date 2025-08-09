package com.example.shoppingapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class FavItemEntity(
    @PrimaryKey(autoGenerate = true)
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
)