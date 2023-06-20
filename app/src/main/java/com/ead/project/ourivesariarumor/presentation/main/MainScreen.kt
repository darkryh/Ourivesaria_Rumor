package com.ead.project.ourivesariarumor.presentation.main

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ead.project.ourivesariarumor.presentation.account.AccountScreen
import com.ead.project.ourivesariarumor.presentation.home.HomeScreen
import com.ead.project.ourivesariarumor.presentation.settings.SettingsScreen
import com.ead.project.ourivesariarumor.presentation.shopping_cart.ShoppingCartScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavHostController,
    viewModel: MainViewModel = hiltViewModel()
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    Scaffold(
        bottomBar =  {
            NavigationBar(

            ) {
                mainScreenItems.forEach { item ->
                    val selected = item.route == backStackEntry.value?.destination?.route

                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.startDestinationId)
                                launchSingleTop = true
                            }
                        },
                        label = {
                            Text(
                                text = item.label,
                                fontWeight = FontWeight.SemiBold,
                                maxLines = 1,
                                textAlign = TextAlign.Center
                                /*overflow = TextOverflow.Ellipsis*/
                            )
                        },
                        icon =  {
                            Icon(imageVector = item.icon,
                                contentDescription = "${item.label} icon"
                            )
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            modifier = Modifier
                .padding(innerPadding),
            navController = navController,
            startDestination = Screen.HomeScreen.route
        ) {
            composable(route = Screen.HomeScreen.route) {
                HomeScreen(
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
            composable(route = Screen.ShoppingCartScreen.route) {
                ShoppingCartScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                )
            }
            composable(route = Screen.AccountScreen.route) {
                AccountScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            start = 16.dp,
                            end = 16.dp,
                            top = 32.dp
                        )
                )
            }
            composable(route = Screen.SettingsScreen.route) {
                SettingsScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            start = 16.dp,
                            end = 16.dp,
                            top = 16.dp
                        )
                )
            }
        }
    }
}