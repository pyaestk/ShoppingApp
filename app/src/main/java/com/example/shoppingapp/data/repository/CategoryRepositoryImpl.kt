package com.example.shoppingapp.data.repository

import com.example.shoppingapp.data.remote.ItemRemoteDataSource
import com.example.shoppingapp.data.remote.RemoteDatasource
import com.example.shoppingapp.domain.model.CategoryModel
import com.example.shoppingapp.domain.model.ItemModel
import com.example.shoppingapp.domain.repository.CategoryRepository
import com.example.shoppingapp.domain.util.Response
import kotlinx.coroutines.flow.Flow

class CategoryRepositoryImpl(
    private val remoteDatasource: RemoteDatasource,
    private val itemRemoteDataSource: ItemRemoteDataSource
): CategoryRepository {
    override fun loadItemByCategory(categoryId: Int): Flow<Response<List<ItemModel>>> {
        return itemRemoteDataSource.loadItemByCategory(categoryId)
    }

    override fun loadCategories(): Flow<Response<List<CategoryModel>>> {
        return remoteDatasource.loadCategories()
    }

}