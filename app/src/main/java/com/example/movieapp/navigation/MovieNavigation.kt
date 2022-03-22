package com.example.movieapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieapp.navigation.MovieScreens

@Composable
fun MyNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = MovieScreens.Homescreen.name) {

        composable(MovieScreens.Homescreen.name) { Homescreen(navController = navController) }
        composable(
            MovieScreens.DetailScreen.name+"/{movieId}",
            arguments = listOf(navArgument("movieId") {
                type = NavType.StringType
            })
            ){ backStackEntry ->
                DetailScreen(navController = navController, movieId = backStackEntry.arguments?.getString("movieId"), )
            }
    }
}