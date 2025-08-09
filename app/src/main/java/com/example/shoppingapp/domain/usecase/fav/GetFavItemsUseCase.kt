package com.example.shoppingapp.domain.usecase.fav

import com.example.shoppingapp.data.local.dao.FavItemsDao
import com.example.shoppingapp.data.local.entity.FavItemEntity
import com.example.shoppingapp.domain.model.ItemModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetFavItemsUseCase(
    private val favItemsDao: FavItemsDao
) {
    operator fun invoke(): Flow<List<ItemModel>> {
        return favItemsDao.getItems().map { items ->
            items.map { it.toModel() }
        }
    }
}

fun FavItemEntity.toModel() = ItemModel(
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