package com.example.shoppingapp.presentation.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.domain.usecase.cart.CartScreenUseCase
import com.example.shoppingapp.domain.util.Response
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CartScreenViewModel(
    private val cartScreenUseCase: CartScreenUseCase,
): ViewModel() {
    private val _state = MutableStateFlow(CartScreenState())
    val state = _state.asStateFlow()

    init {
        getCartItems()
    }
    fun getCartItems() = viewModelScope.launch {
        cartScreenUseCase.getCartItemUseCase().collect { response ->
            when (response) {
                is Response.Error<*> -> {
                    _state.value = _state.value.copy(
                        error = response.message  ?: "An unknown error occurred",
                        isLoading = false
                    )
                }
                is Response.Loading<*> -> {
                    _state.value = _state.value.copy(
                        isLoading = true,
                        error = null
                    )
                }
                is Response.Success<*> -> {
                    _state.value = _state.value.copy(
                        cartItems = response.data?: emptyList(),
                        isLoading = false,
                        error = null,
                        totalPrice = response.data?.sumOf { item -> item.price * item.quantity }
                            ?.toDouble() ?: 0.0
                    )
                }
            }
        }
    }

    fun onEvent(event: CartScreenEvent) {
        when (event) {
            is CartScreenEvent.IncreaseQuantity -> {
                increaseQuantity(event.itemId)
            }

            is CartScreenEvent.DecreaseQuantity -> {
                decreaseQuantity(event.itemId)
            }

            is CartScreenEvent.RemoveItem -> {
                removeItem(event.itemId)
            }
        }
    }

    private fun increaseQuantity(itemId: Int) = viewModelScope.launch {
        cartScreenUseCase.increaseCartItemQtyUseCase(itemId).let { response ->
            when(response){
                is Response.Error<*> -> {
                    _state.value = _state.value.copy(
                        error = response.message  ?: "An unknown error occurred",
                        isLoading = false
                    )
                }
                is Response.Loading<*> -> {
                    _state.value = _state.value.copy(
                        isLoading = true,
                        error = null
                    )
                }
                is Response.Success<*> -> {
                    getCartItems()
                }
            }
        }
        /*when (val response = increaseCartItemQtyUseCase(itemId)) {
            is Response.Success -> {
                getCartItems() // refresh cart after successful update
            }
            is Response.Error -> {
                // optionally show error
            }
            is Response.Loading -> {
                // optionally show loading
            }
        }*/
    }

    private fun decreaseQuantity(itemId: Int) = viewModelScope.launch {
        cartScreenUseCase.decreaseCartItemQtyUseCase(itemId).let { response ->
            when(response){
                is Response.Error<*> -> {
                    _state.value = _state.value.copy(
                        error = response.message  ?: "An unknown error occurred",
                        isLoading = false
                    )
                }
                is Response.Loading<*> -> {
                    _state.value = _state.value.copy(
                        isLoading = true,
                        error = null
                    )
                }
                is Response.Success<*> -> {
                    getCartItems()
                }
            }
        }
        /*when (val response = increaseCartItemQtyUseCase(itemId)) {
            is Response.Success -> {
                getCartItems() // refresh cart after successful update
            }
            is Response.Error -> {
                // optionally show error
            }
            is Response.Loading -> {
                // optionally show loading
            }
        }*/
    }

    private fun removeItem(itemId: Int) = viewModelScope.launch {
        cartScreenUseCase.removeCartItemUseCase(itemId).let { response ->
            when(response){
                is Response.Error<*> -> {
                    _state.value = _state.value.copy(
                        error = response.message  ?: "An unknown error occurred",
                        isLoading = false
                    )
                }
                is Response.Loading<*> -> {
                    _state.value = _state.value.copy(
                        isLoading = true,
                        error = null
                    )
                }
                is Response.Success<*> -> {
                    getCartItems()
                }
            }

        }
    }


}
