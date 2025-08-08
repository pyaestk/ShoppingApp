package com.example.shoppingapp.presentation.navigation


sealed class NavRoute(
    val route: String
){
    object MenuNavigation: NavRoute("newsNavigation")
    object HomeScreen: NavRoute("home")
    object CartScreen: NavRoute("cart")
    object FavoriteScreen: NavRoute("favorite")
    object OrderScreen: NavRoute("orders")
    object ProfileScreen: NavRoute("profile")

    object DetailScreen : NavRoute("detail/{itemId}") {
        fun createRoute(itemId: Int) = "detail/${itemId}"
    }
    object CategoryScreen: NavRoute("category")
}
