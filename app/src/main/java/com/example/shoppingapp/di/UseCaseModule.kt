package com.example.shoppingapp.di

import com.example.shoppingapp.domain.usecase.cart.CartScreenUseCase
import com.example.shoppingapp.domain.usecase.cart.DecreaseCartItemQtyUseCase
import com.example.shoppingapp.domain.usecase.cart.GetCartItemUseCase
import com.example.shoppingapp.domain.usecase.cart.IncreaseCartItemQtyUseCase
import com.example.shoppingapp.domain.usecase.cart.RemoveCartItemUseCase
import com.example.shoppingapp.domain.usecase.category.GetItemsByCategoryUseCase
import com.example.shoppingapp.domain.usecase.detail.AddItemToCartUseCase
import com.example.shoppingapp.domain.usecase.detail.DetailScreenUseCase
import com.example.shoppingapp.domain.usecase.detail.GetItemDetailUseCase
import com.example.shoppingapp.domain.usecase.home.GetBannersUseCase
import com.example.shoppingapp.domain.usecase.home.GetCategoryUseCase
import com.example.shoppingapp.domain.usecase.home.GetItemsUseCase
import com.example.shoppingapp.domain.usecase.home.HomeUseCases
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
        GetItemDetailUseCase(
            get()
        )
    }
    factory {
        DetailScreenUseCase(
            getItemDetailUseCase = get(),
            addItemToCartUseCase = get()
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
    factory {
        RemoveCartItemUseCase(
            get()
        )
    }
    factory {
        CartScreenUseCase(
            getCartItemUseCase = get(),
            increaseCartItemQtyUseCase = get(),
            decreaseCartItemQtyUseCase = get(),
            removeCartItemUseCase = get()
        )
    }

}