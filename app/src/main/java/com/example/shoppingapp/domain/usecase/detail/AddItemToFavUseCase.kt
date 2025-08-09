package com.example.shoppingapp.domain.usecase.detail

import com.example.shoppingapp.data.local.dao.FavItemsDao
import com.example.shoppingapp.domain.model.ItemModel
import com.example.shoppingapp.domain.usecase.util.toEntity

class AddItemToFavUseCase(
    private val dao: FavItemsDao
) {
    suspend operator fun invoke(item: ItemModel) {
        dao.upsertItem(item.toEntity())
    }
}


