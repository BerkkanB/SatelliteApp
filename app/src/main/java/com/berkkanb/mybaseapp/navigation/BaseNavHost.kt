package com.berkkanb.mybaseapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.berkkanb.mybaseapp.presentation.detail.DetailScreen
import com.berkkanb.mybaseapp.presentation.home.HomeScreen

@Composable
fun BaseNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = BaseNavGraph.HOME_GRAPH) {
        composable(BaseNavGraph.HOME_GRAPH) {
            HomeScreen(
                navigateToDetailScreen = { id, title ->
                    navController.navigate("${BaseNavGraph.DETAIL_GRAPH}/$id/$title")
                }
            )
        }
        composable(
            "${BaseNavGraph.DETAIL_GRAPH}/{detailId}/{title}",
            arguments = listOf(
                navArgument("detailId") { type = NavType.StringType },
                navArgument("title") { type = NavType.StringType }
            )
        ) {
            DetailScreen()
        }
    }
}
