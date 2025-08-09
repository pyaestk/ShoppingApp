package com.example.shoppingapp.domain.usecase.util

import com.example.shoppingapp.data.local.entity.FavItemEntity
import com.example.shoppingapp.domain.model.ItemModel

fun ItemModel.toEntity() = FavItemEntity(
    id  = id,
    categoryId = this.categoryId,
    description = this.description,
    picUrl = this.picUrl,
    price = this.price,
    rating = this.rating,
    sellerName = this.sellerName,
    sellerPic = this.sellerPic,
    sellerTell = this.sellerTell,
    showRecommended = this.showRecommended,
    size = this.size,
    title = this.title
)