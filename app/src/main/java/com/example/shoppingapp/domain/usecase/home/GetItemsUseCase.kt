package com.example.shoppingapp.domain.usecase.home

import com.example.shoppingapp.domain.model.ItemModel
import com.example.shoppingapp.domain.repository.HomeRepository
import com.example.shoppingapp.domain.util.Response
import kotlinx.coroutines.flow.Flow

class GetItemsUseCase(
    private val repository: HomeRepository
) {
    operator fun invoke(): Flow<Response<List<ItemModel>>> = repository.loadItems()
}