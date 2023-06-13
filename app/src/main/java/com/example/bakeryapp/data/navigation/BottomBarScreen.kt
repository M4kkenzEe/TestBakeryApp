package com.example.bakeryapp.data.navigation

import com.example.bakeryapp.R

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: Int,
) {
    object Home : BottomBarScreen(
        route = "home",
        title = "Главная",
        icon = R.drawable.ic_home_2,
    )

    object Search : BottomBarScreen(
        route = "search",
        title = "Поиск",
        icon = R.drawable.ic_search,
    )

    object Basket : BottomBarScreen(
        route = "basket",
        title = "Корзина",
        icon = R.drawable.ic_basket,
    )

    object Account : BottomBarScreen(
        route = "account",
        title = "Аккаунт",
        icon = R.drawable.ic_account,
    )

    //Ниже идут скрины, иконки и названия которых не будут использованы
    // тк. к ним переход осуществляется не через нижнее меню

    object DishesScreen : BottomBarScreen(
        route = "dishes",
        title = "Меню",
        icon = R.drawable.ic_account
    )
}