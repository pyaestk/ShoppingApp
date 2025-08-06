package com.example.shoppingapp.domain.usecase.cart

data class CartScreenUseCase(
    val getCartItemUseCase: GetCartItemUseCase,
    val increaseCartItemQtyUseCase: IncreaseCartItemQtyUseCase,
    val decreaseCartItemQtyUseCase: DecreaseCartItemQtyUseCase,
    val removeCartItemUseCase: RemoveCartItemUseCase
)