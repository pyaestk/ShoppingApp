package com.example.shoppingapp.di

import com.example.shoppingapp.domain.usecase.AddItemToCartUseCase
import com.example.shoppingapp.domain.usecase.DecreaseCartItemQtyUseCase
import com.example.shoppingapp.domain.usecase.GetBannersUseCase
import com.example.shoppingapp.domain.usecase.GetCartItemUseCase
import com.example.shoppingapp.domain.usecase.GetCategoryUseCase
import com.example.shoppingapp.domain.usecase.GetItemsByCategoryUseCase
import com.example.shoppingapp.domain.usecase.GetItemsUseCase
import com.example.shoppingapp.domain.usecase.HomeUseCases
import com.example.shoppingapp.domain.usecase.IncreaseCartItemQtyUseCase
import org.koin.dsl.module

val UseCaseModule = module {
    factory {
        GetBannersUseCase(
            homeRepository = get()
        )
    }
    factory {
        GetCategoryUseCase(
            categoryRepository = get()
        )
    }
    factory {
        GetItemsUseCase(
            get()
        )
    }
    factory {
        HomeUseCases(
            getBannersUseCase = get(),
            getCategoryUseCase = get(),
            getItemsUseCase = get(),
            addItemToCartUseCase = get()
        )
    }
    factory {
        GetItemsByCategoryUseCase(
            categoryRepository = get()
        )
    }
    factory {
        AddItemToCartUseCase(
            get()
        )
    }
    factory {
        GetCartItemUseCase(
            get()
        )
    }
    factory {
        IncreaseCartItemQtyUseCase(
            get()
        )
    }
    factory {
        DecreaseCartItemQtyUseCase(
            get()
        )
    }
}