package com.example.shoppingapp.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.domain.usecase.AddItemToCartUseCase
import kotlinx.coroutines.launch

class DetailScreenViewModel(
    private val addItemToCartUseCase: AddItemToCartUseCase
): ViewModel() {

    fun onEvent(event: DetailScreenEvent){
        when(event){
            is DetailScreenEvent.AddItemToCart -> {
                viewModelScope.launch {
                    addItemToCartUseCase(
                        id = event.id,
                        quantity = event.quantity,
                        size = event.size
                    )
                }
            }
            DetailScreenEvent.RemoveSideEffect -> {

            }
        }
    }

}