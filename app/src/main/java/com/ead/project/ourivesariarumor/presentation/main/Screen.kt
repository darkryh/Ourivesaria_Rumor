package com.ead.project.ourivesariarumor.presentation.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val label: String, val icon: ImageVector) {
    object HomeScreen : Screen("home", "Home", Icons.Filled.Home)
    object ShoppingCartScreen : Screen("shopping_cart", "Shopping Cart", Icons.Filled.ShoppingCart)
    object AccountScreen : Screen("account","Account",Icons.Default.AccountCircle)
    object SettingsScreen : Screen("settings","Settings",Icons.Default.Settings)
}
