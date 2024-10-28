package com.example.juventus

import Favourites
import FavouritesScreen
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Stars
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
            label = { Text("Team") },
            selected = currentRoute(navController) == "team_home",
            onClick = {
                navController.navigate("team_home") {
                    popUpTo(navController.graph.startDestinationId)
                    launchSingleTop = true
                }
            }
        )

        NavigationBarItem(
            icon = { Icon(Icons.Filled.Stars, contentDescription = "Favorites") },
            label = { Text("Favorites") },
            selected = currentRoute(navController) == "favourites",
            onClick = {
                navController.navigate("favourites") {
                    popUpTo(navController.graph.startDestinationId)
                    launchSingleTop = true
                }
            }
        )

        NavigationBarItem(
            icon = { Icon(Icons.Filled.EmojiEvents, contentDescription = "Achievements") }, // Trophy icon
            label = { Text("Achievements") },
            selected = currentRoute(navController) == "achievements",
            onClick = {
                navController.navigate("achievements") {
                    popUpTo(navController.graph.startDestinationId)
                    launchSingleTop = true
                }
            }
        )
    }
}
@Composable
fun NavigationHost(
    navController: NavHostController,
    favouritesViewModel: Favourites,
    players: Map<String, List<Player>>,
    paddingValues: PaddingValues
) {
    NavHost(navController, startDestination = "team_home", modifier = Modifier.padding(paddingValues)) {
        composable("team_home") {
            TeamHomeScreen(players = players, favouritesViewModel = favouritesViewModel)
        }
        composable("favourites") {
            FavouritesScreen(favouritesViewModel = favouritesViewModel)
        }
        composable("achievements") {
            AchievementsScreen()
        }
    }
}


@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}