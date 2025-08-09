package com.example.shoppingapp.di


import com.example.shoppingapp.presentation.cart.CartScreenViewModel
import com.example.shoppingapp.presentation.category.CategoryScreenViewModel
import com.example.shoppingapp.presentation.detail.DetailScreenViewModel
import com.example.shoppingapp.presentation.fav.FavScreenViewModel
import com.example.shoppingapp.presentation.home.HomeScreenViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        HomeScreenViewModel(
            homeUseCases = get()
        )
    }
    viewModel {
        CategoryScreenViewModel(
            get()
        )
    }
    viewModel {
        DetailScreenViewModel(
            get()
        )
    }
    viewModel {
        CartScreenViewModel(
            get(),
        )
    }
    viewModel {
        FavScreenViewModel(
            get()
        )
    }
}