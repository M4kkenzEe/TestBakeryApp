package com.example.bakeryapp.data.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bakeryapp.data.navigation.BottomBarScreen
import com.example.bakeryapp.presentation.view.*

@Composable
fun BottomNavGraph(navController: NavHostController) {

    NavHost(navController = navController, startDestination = BottomBarScreen.Home.route) {

        composable(BottomBarScreen.Home.route) {
            HomeScreen(
                nextScreen = { navController.navigate(BottomBarScreen.DishesScreen.route) }
            )
        }

        composable(BottomBarScreen.Search.route) {
            SearchScreen()
        }

        composable(BottomBarScreen.Basket.route) {
            BasketScreen()
        }

        composable(BottomBarScreen.Account.route) {
            AccountScreen()
        }

        composable(BottomBarScreen.DishesScreen.route) {
            DishScreen(
                backScreen = { navController.navigate(BottomBarScreen.Home.route) }
            )
        }

    }
}