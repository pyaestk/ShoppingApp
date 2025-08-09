package com.example.shoppingapp.presentation.detail

import com.example.shoppingapp.domain.model.ItemModel

sealed class DetailScreenEvent {
    data class AddItemToCart(val id: Int, val quantity: Int, val size: String) : DetailScreenEvent()

    data class AddRemoveItemToFav(val itemModel: ItemModel) : DetailScreenEvent()
    object RemoveSideEffect: DetailScreenEvent()
}