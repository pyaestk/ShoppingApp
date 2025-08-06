package com.example.shoppingapp.domain.usecase.detail

data class DetailScreenUseCase(
    val addItemToCartUseCase: AddItemToCartUseCase,
    val getItemDetailUseCase: GetItemDetailUseCase
)