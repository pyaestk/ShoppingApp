package com.example.shoppingapp.presentation.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.domain.usecase.category.GetItemsByCategoryUseCase
import com.example.shoppingapp.domain.util.Response
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CategoryScreenViewModel(
    private val getItemsByCategoryUseCase: GetItemsByCategoryUseCase
): ViewModel() {

    private val _categoryScreenState = MutableStateFlow(CategoryScreenState())
    val categoryScreenState: StateFlow<CategoryScreenState> = _categoryScreenState.asStateFlow()

    fun loadItemsByCategory(categoryId: Int) = viewModelScope.launch {
        getItemsByCategoryUseCase(categoryId).collect { resource ->
            when(resource){
                is Response.Error<*> -> {
                    _categoryScreenState.value = _categoryScreenState.value.copy(
                        isLoading = false,
                        error = resource.message ?: "An unknown error occurred"
                    )
                }
                is Response.Loading<*> -> {
                    _categoryScreenState.value = _categoryScreenState.value.copy(
                        isLoading = true,
                        error = null
                    )
                }
                is Response.Success<*> -> {
                    _categoryScreenState.value = _categoryScreenState.value.copy(
                        isLoading = false,
                        items = resource.data ?: emptyList(),
                        error = null
                    )
                }
            }
        }
    }

}