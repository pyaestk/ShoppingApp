package com.example.shoppingapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppingapp.domain.usecase.home.HomeUseCases
import com.example.shoppingapp.domain.util.Response
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    val homeUseCases: HomeUseCases
): ViewModel() {

    private val _homeScreenState =
        MutableStateFlow(HomeScreenState()) // Initialize with default HomeScreenState
    val homeScreenState: StateFlow<HomeScreenState> = _homeScreenState.asStateFlow()

    init {
        loadBanners()
        loadCategories()
        loadItems()
    }

    private fun loadBanners() = viewModelScope.launch {
        homeUseCases.getBannersUseCase().collect { resource ->
            when (resource) {
                is Response.Error -> {
                    _homeScreenState.update { currentState ->
                        currentState.copy(
                            isLoading = false,
                            error = resource.message ?: "An unknown error occurred"
                        )
                    }
                }
                is Response.Loading -> {
                    _homeScreenState.update { currentState ->
                        currentState.copy(
                            isLoading = true,
                            error = null
                        )
                    }
                }
                is Response.Success -> {
                    _homeScreenState.update { currentState ->
                        currentState.copy(
                            isLoading = false,
                            banners = resource.data ?: emptyList(),
                            error = null
                        )
                    }
                }
            }
        }
    }

    private fun loadCategories() = viewModelScope.launch {
        homeUseCases.getCategoryUseCase().collect { resource ->
            when(resource){
                is Response.Error<*> -> {
                    _homeScreenState.update { currentState ->
                        currentState.copy(
                            isLoading = false,
                            error = resource.message ?: "An unknown error occurred"
                        )
                    }
                }
                is Response.Loading<*> -> {
                    _homeScreenState.update { currentState ->
                        currentState.copy(
                            isLoading = true,
                            error = null
                        )
                    }
                }
                is Response.Success<*> -> {
                    _homeScreenState.update { currentState ->
                        currentState.copy(
                            isLoading = false,
                            categories = resource.data ?: emptyList(),
                            error = null
                        )
                    }
                }
            }
        }
    }

    private fun loadItems() = viewModelScope.launch {
        homeUseCases.getItemsUseCase().collect { resource ->
            when(resource){
                is Response.Error<*> -> {
                    _homeScreenState.update { currentState ->
                        currentState.copy(
                            isLoading = false,
                            error = resource.message ?: "An unknown error occurred"
                        )
                    }
                }
                is Response.Loading<*> -> {
                    _homeScreenState.update { currentState ->
                        currentState.copy(
                            isLoading = true,
                            error = null
                        )
                    }
                }
                is Response.Success<*> -> {
                    _homeScreenState.update { currentState ->
                        currentState.copy(
                            isLoading = false,
                            items = resource.data ?: emptyList(),
                            error = null
                        )
                    }
                }
            }
        }
    }

}