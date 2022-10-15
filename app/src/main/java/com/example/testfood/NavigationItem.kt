package com.example.testfood

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationItem(var route: String, var icon: ImageVector, var title: String) {
    object Menu : NavigationItem("menu", Icons.Filled.Menu, "Меню")
    object Profile : NavigationItem("profile", Icons.Filled.AccountCircle, "Профиль")
    object Basket : NavigationItem("basket", Icons.Filled.ShoppingCart, "Корзина")
    object PizzaCategory : NavigationItem("pizza", Icons.Filled.ShoppingCart, "pizza")
    object ComboCategory : NavigationItem("combo", Icons.Filled.ShoppingCart, "combo")
    object DessertsCategory : NavigationItem("desserts", Icons.Filled.ShoppingCart, "desserts")
    object DrinksCategory : NavigationItem("drinks", Icons.Filled.ShoppingCart, "drinks")
    object DetailDrink : NavigationItem("detaildrink", Icons.Filled.ShoppingCart, "detaildrink")
    object DetailCombo : NavigationItem("detailcombo", Icons.Filled.ShoppingCart, "detailcombo")
    object DetailPizza : NavigationItem("detailpizaz", Icons.Filled.ShoppingCart, "detailpizaz")
    object DetailDesserts : NavigationItem("detaildesserts", Icons.Filled.ShoppingCart, "detaildesserts")
    object QrPage : NavigationItem("qrPage", Icons.Filled.ShoppingCart, "qrPage")
}