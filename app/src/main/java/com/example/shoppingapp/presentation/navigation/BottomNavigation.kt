package com.example.shoppingapp.presentation.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.shoppingapp.R
import com.example.shoppingapp.domain.model.CategoryModel
import com.example.shoppingapp.domain.model.ItemModel
import com.example.shoppingapp.presentation.cart.CartScreen
import com.example.shoppingapp.presentation.cart.CartScreenEvent
import com.example.shoppingapp.presentation.category.CategoryScreen
import com.example.shoppingapp.presentation.detail.DetailScreen
import com.example.shoppingapp.presentation.home.HomeScreen
import com.example.shoppingapp.presentation.navigation.component.BottomNavigationBar

@Composable
fun BottomNavigation() {

    val navController = rememberNavController()

    val showBottomNav = remember {
        mutableStateOf(true)
    }

    val items = remember {
        listOf(
            BottomNavItems.Home,
            BottomNavItems.Cart,
            BottomNavItems.Favorite,
            BottomNavItems.Order,
            BottomNavItems.Profile
        )
    }
    val backstackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableStateOf(0)
    }
    selectedItem = when (backstackState?.destination?.route) {
        NavRoute.HomeScreen.route -> 0
        NavRoute.CartScreen.route -> 1
        NavRoute.FavoriteScreen.route -> 2
        NavRoute.OrderScreen.route -> 3
        NavRoute.ProfileScreen.route -> 4
        else -> 0
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (showBottomNav.value){
                BottomNavigationBar(
                    selectedItem = selectedItem,
                    items = items,
                    onClick = { item ->
                        navController.navigate(item.route) {
                            navController.graph.startDestinationRoute?.let { startRoute ->
                                popUpTo(startRoute) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }

        },

    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            NavHost(
                navController = navController,
                startDestination = NavRoute.HomeScreen.route,
            ) {
                composable(
                    NavRoute.HomeScreen.route,
                ) {
                    HomeScreen(
                        navigateToDetail = { item ->
                            navigateToDetails(
                                navController = navController,
                                product = item
                            )
                        },
                        navigateToCategory = { category ->
                            navigateToCategory(
                                navController = navController,
                                categoryModel = category
                            )
                        }
                    )
                    showBottomNav.value = true
                }
                composable(
                    NavRoute.CartScreen.route,
                ) {
                    CartScreen()
                    showBottomNav.value = true
                }
                composable(
                    NavRoute.FavoriteScreen.route,
                ) {
                    Box(){

                    }
                    showBottomNav.value = true
                }
                composable(
                    NavRoute.OrderScreen.route,
                ) {
                    Box(){

                    }
                    showBottomNav.value = true
                }
                composable(
                    NavRoute.ProfileScreen.route,
                    enterTransition = { defaultEnterTransition() },
                    exitTransition = { defaultExitTransition() }
                ) {
                    Box(){

                    }
                    showBottomNav.value = true
                }
                composable(
                    NavRoute.DetailScreen.route,
                    enterTransition = { defaultEnterTransition() },
                    exitTransition = { defaultExitTransition() },
                    popExitTransition = { defaultPopExitTransition() },
                    popEnterTransition = { defaultPopEnterTransition() }
                ) {
                    navController.previousBackStackEntry?.savedStateHandle?.get<ItemModel>("item")?.let { item ->
                        DetailScreen(
                            item = item,
                            onBackClick = {
                                navController.navigateUp()
                            }
                        )
                    }
                    showBottomNav.value = false
                }
                composable(
                    NavRoute.CategoryScreen.route,
                    enterTransition = { defaultEnterTransition() },
                    exitTransition = { defaultExitTransition() },
                    popExitTransition = { defaultPopExitTransition() },
                    popEnterTransition = { defaultPopEnterTransition() }
                ) {
                    navController.previousBackStackEntry?.savedStateHandle?.get<CategoryModel>("category")?.let { category ->
                        CategoryScreen(
                            category = category,
                            onBackClick = {
                                navController.navigateUp()
                            },
                            onItemClick = { item ->
                                navigateToDetails(
                                    navController = navController,
                                    product = item
                                )
                            }

                        )
                    }
                    showBottomNav.value = false
                }
            }
        }
    }

}

private fun navigateToDetails(navController: NavController, product: ItemModel) {

    navController.currentBackStackEntry?.savedStateHandle?.set(
        key = "item",
        value = product
    )
    navController.navigate(NavRoute.DetailScreen.route)
}

private fun navigateToCategory(navController: NavController, categoryModel: CategoryModel) {
    navController.currentBackStackEntry?.savedStateHandle?.set(
        key = "category",
        value = categoryModel
    )
    navController.navigate(NavRoute.CategoryScreen.route)
}

sealed class BottomNavItems(val route: String, val title: String, val icon: Int) {
    object Home : BottomNavItems(NavRoute.HomeScreen.route, "Home", icon = R.drawable.btn_1)
    object Cart : BottomNavItems(NavRoute.CartScreen.route, "Cart", icon = R.drawable.btn_2)
    object Favorite : BottomNavItems(NavRoute.FavoriteScreen.route, "Favorite", icon = R.drawable.btn_3)
    object Order : BottomNavItems(NavRoute.OrderScreen.route, "Order", icon = R.drawable.btn_4)
    object Profile : BottomNavItems(NavRoute.ProfileScreen.route, "Profile", icon = R.drawable.btn_5)
}

fun defaultEnterTransition(): EnterTransition =
    slideInHorizontally(
        initialOffsetX = { fullWidth -> fullWidth },
        animationSpec = tween(700)
    ) + fadeIn(animationSpec = tween(700))

fun defaultExitTransition(): ExitTransition =
    slideOutHorizontally(
        targetOffsetX = { fullWidth -> -fullWidth },
        animationSpec = tween(700)
    ) + fadeOut(animationSpec = tween(700))

fun defaultPopEnterTransition(): EnterTransition =
    slideInHorizontally(
        initialOffsetX = { fullWidth -> -fullWidth },
        animationSpec = tween(700)
    ) + fadeIn(animationSpec = tween(700))

fun defaultPopExitTransition(): ExitTransition =
    slideOutHorizontally(
        targetOffsetX = { fullWidth -> fullWidth },
        animationSpec = tween(700)
    ) + fadeOut(animationSpec = tween(700))