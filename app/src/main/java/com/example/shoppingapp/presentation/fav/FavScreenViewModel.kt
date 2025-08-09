package com.example.shoppingapp.presentation.fav

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.domain.usecase.fav.FavScreenUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FavScreenViewModel(
    private val favScreenUseCase: FavScreenUseCase
): ViewModel() {
    private val _state = MutableStateFlow(FavScreenState())
    val state = _state.asStateFlow()

    init {
        getFavItems()
    }

    private fun getFavItems() = viewModelScope.launch {
        favScreenUseCase.getFavItemsUseCase().collect { items ->
            _state.value = _state.value.copy(
                items = items
            )
        }
    }
}