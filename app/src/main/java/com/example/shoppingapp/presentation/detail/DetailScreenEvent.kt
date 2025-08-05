package com.example.shoppingapp.presentation.detail

sealed class DetailScreenEvent {
    data class AddItemToCart(val id: Int, val quantity: Int, val size: String) : DetailScreenEvent()
    object RemoveSideEffect: DetailScreenEvent()
}