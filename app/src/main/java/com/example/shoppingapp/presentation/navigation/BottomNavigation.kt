package com.example.shoppingapp.presentation.navigation

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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.shoppingapp.R
import com.example.shoppingapp.domain.model.CategoryModel
import com.example.shoppingapp.presentation.cart.CartScreen
import com.example.shoppingapp.presentation.category.CategoryScreen
import com.example.shoppingapp.presentation.detail.DetailScreen
import com.example.shoppingapp.presentation.home.HomeScreen
import com.example.shoppingapp.presentation.navigation.component.BottomNavigationBar
import com.example.shoppingapp.presentation.profile.ProfileScreen

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
            BottomNavItems.Order,
            BottomNavItems.Profile
        )
    }
    val backstackState = navController.currentBackStackEntryAsState().value

    var selectedItem by rememberSaveable {
        mutableStateOf(0)
    }

    val route = backstackState?.destination?.route

    selectedItem = when (route) {
        NavRoute.HomeScreen.route -> 0
        NavRoute.CartScreen.route -> 1
        NavRoute.OrderScreen.route -> 2
        NavRoute.ProfileScreen.route -> 3
        else -> selectedItem
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

        }) { paddingValues ->

        NavHost(
            navController = navController,
            startDestination = NavRoute.HomeScreen.route,
            modifier = Modifier,
        ) {

            composable(
                NavRoute.HomeScreen.route,
            ) {
                HomeScreen(
                    paddingValues = paddingValues,
                    navigateToDetail = { item ->
                        navigateToDetails(
                            navController = navController,
                            itemId = item.id,
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
                CartScreen(
                    paddingValues = paddingValues,
                    onItemClick = { item ->
                        navigateToDetails(
                            navController = navController,
                            itemId = item.itemId,
                        )
                    }
                )
                showBottomNav.value = true
            }

            composable(
                NavRoute.OrderScreen.route,
            ) {
                Box(
                    modifier = Modifier.padding(paddingValues)
                ) {

                }
                showBottomNav.value = true
            }
            composable(
                NavRoute.ProfileScreen.route,

                ) {
                ProfileScreen(
                    paddingValues = paddingValues
                )
                showBottomNav.value = true
            }
            composable(
                NavRoute.DetailScreen.route, // Use the route directly from NavRoute
                arguments = listOf(navArgument("itemId") {
                    type = NavType.IntType
                })
            ) { backStackEntry ->
                val itemId = backStackEntry.arguments?.getInt("itemId") ?: return@composable

                DetailScreen(
                    itemId = itemId,
                    onBackClick = { navController.navigateUp() }
                )
                showBottomNav.value = false
            }
            composable(
                NavRoute.CategoryScreen.route,
            ) {
                navController.previousBackStackEntry?.savedStateHandle?.get<CategoryModel>("category")
                    ?.let { category ->
                        CategoryScreen(
                            category = category,
                            onBackClick = {
                                navController.navigateUp()
                            },
                            onItemClick = { item ->
                                navigateToDetails(
                                    navController = navController,
                                    itemId = item.id,
                                )
                            }

                        )
                    }
                showBottomNav.value = false
            }
        }
    }


}

private fun navigateToDetails(navController: NavController, itemId: Int) {
    navController.navigate(NavRoute.DetailScreen.createRoute(itemId))
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

    object Order : BottomNavItems(NavRoute.OrderScreen.route, "Order", icon = R.drawable.btn_4)
    object Profile :
        BottomNavItems(NavRoute.ProfileScreen.route, "Profile", icon = R.drawable.btn_5)
}
