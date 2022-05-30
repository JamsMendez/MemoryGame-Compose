package com.jamsmendez.memorygame_compose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.jamsmendez.memorygame_compose.ui.screen.GameRoute
import com.jamsmendez.memorygame_compose.ui.screen.WelcomeRoute

/*
sealed class Screen(val route: String) {
  object Welcome: Screen(route = "welcome")
  object Game: Screen(route = "game")
  object Scores: Screen(route = "scores_screen")
}

@Composable
fun SetupNavGraph(navController: NavHostController) {
  NavHost(
    navController = navController,
    startDestination = Screen.Welcome.route,
  ) {
    composable(route = Screen.Welcome.route) {
      WelcomeScreen(navController = navController)
    }
    composable(
      route = "${Screen.Game.route}/{difficulty}",
      arguments = listOf(navArgument("difficulty") { type = NavType.StringType })
    ) {
      it.arguments?.getString("difficulty")?.let { difficulty ->
        GameScreen(
          navController = navController,
          difficulty = DifficultyType.valueOf(difficulty)
        )
      }
    }
    composable(route = Screen.Scores.route) {
    }
  }
}*/

@Composable
fun SetupNavGraph(navController: NavHostController) {
  NavHost(
    navController = navController,
    startDestination = WelcomeRoute.route,
  ) {
    WelcomeRoute.composable(this, navHostController = navController)
    GameRoute.composable(this, navHostController = navController)
    //ScoreRoute.composable(this, navHostController = navController)
  }
}
