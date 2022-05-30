package com.jamsmendez.memorygame_compose.ui.screen

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jamsmendez.memorygame_compose.navigation.NavRoute
import com.jamsmendez.memorygame_compose.ui.component.Welcome
import com.jamsmendez.memorygame_compose.ui.viewmodel.GameViewModel
import com.jamsmendez.memorygame_compose.ui.viewmodel.WelcomeViewModel

object WelcomeRoute: NavRoute<WelcomeViewModel> {

  override val route = "welcome/"

  @Composable
  override fun viewModel(): WelcomeViewModel = hiltViewModel()

  @Composable
  override fun Content(viewModel: WelcomeViewModel) = WelcomeScreen()
}

@Composable
fun WelcomeScreen(
  viewModel: WelcomeViewModel = hiltViewModel(),
) {
  Welcome(
    onSelected = viewModel::onSelected
  )
}