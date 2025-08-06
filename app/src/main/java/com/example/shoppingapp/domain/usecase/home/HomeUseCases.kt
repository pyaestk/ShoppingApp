package com.example.shoppingapp.domain.usecase.home

import com.example.shoppingapp.domain.usecase.detail.AddItemToCartUseCase

data class HomeUseCases(
    val getBannersUseCase: GetBannersUseCase,
    val getCategoryUseCase: GetCategoryUseCase,
    val getItemsUseCase: GetItemsUseCase,
    val addItemToCartUseCase: AddItemToCartUseCase
)