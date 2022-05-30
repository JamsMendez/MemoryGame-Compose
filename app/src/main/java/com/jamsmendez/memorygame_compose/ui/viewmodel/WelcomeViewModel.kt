package com.jamsmendez.memorygame_compose.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.jamsmendez.memorygame_compose.navigation.RouteNavigator
import com.jamsmendez.memorygame_compose.util.DifficultyType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel
@Inject constructor(
  private val routerNavigator: RouteNavigator,
) : ViewModel(), RouteNavigator by routerNavigator {

  fun onSelected(difficulty: DifficultyType) {
    val route = "game/$difficulty"
    navigateToRoute(route)
  }
}