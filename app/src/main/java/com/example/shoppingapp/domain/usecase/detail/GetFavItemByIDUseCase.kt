package com.example.shoppingapp.domain.usecase.detail

import com.example.shoppingapp.data.local.dao.FavItemsDao
import com.example.shoppingapp.domain.usecase.fav.toModel

class GetFavItemByIDUseCase(
    private val dao: FavItemsDao
) {
    suspend operator fun invoke(id: Int) = dao.getItemById(id)?.toModel()
}