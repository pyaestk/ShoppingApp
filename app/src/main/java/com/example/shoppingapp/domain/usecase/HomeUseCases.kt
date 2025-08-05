package com.example.shoppingapp.domain.usecase

data class HomeUseCases(
    val getBannersUseCase: GetBannersUseCase,
    val getCategoryUseCase: GetCategoryUseCase,
    val getItemsUseCase: GetItemsUseCase,
    val addItemToCartUseCase: AddItemToCartUseCase
)