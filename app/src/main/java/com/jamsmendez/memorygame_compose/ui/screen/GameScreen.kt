package com.jamsmendez.memorygame_compose.ui.screen

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalConfiguration
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.jamsmendez.memorygame_compose.navigation.NavRoute
import com.jamsmendez.memorygame_compose.navigation.getOrThrow
import com.jamsmendez.memorygame_compose.ui.component.Dialog
import com.jamsmendez.memorygame_compose.ui.component.Game
import com.jamsmendez.memorygame_compose.ui.viewmodel.GameViewModel
import com.jamsmendez.memorygame_compose.util.DifficultyType

const val KEY_DIFFICULTY = "difficulty"

object GameRoute: NavRoute<GameViewModel> {

  override val route = "game/{$KEY_DIFFICULTY}"

  override fun getArguments(): List<NamedNavArgument> = listOf(
    navArgument(KEY_DIFFICULTY) { type = NavType.StringType })

  @Composable
  override fun viewModel(): GameViewModel = hiltViewModel()

  @Composable
  override fun Content(viewModel: GameViewModel) = GameScreen()

  fun get(difficulty: DifficultyType): String = route.replace("{$KEY_DIFFICULTY}", "$difficulty")

  fun getDifficulty(savedStateHandle: SavedStateHandle) = savedStateHandle.getOrThrow<String>(KEY_DIFFICULTY)
}

@Composable
fun GameScreen(
  viewModel: GameViewModel = hiltViewModel(),
) {
  val valueTimer by viewModel.timerDownState
  val board by viewModel.boardState
  val dialogState by viewModel.dialogState

  val (cards, moves, remainingPairs, difficulty) = board

  val configuration = LocalConfiguration.current
  val isPortrait = configuration.screenHeightDp > configuration.screenWidthDp

  val cells = when (difficulty) {
    DifficultyType.EASY, DifficultyType.MEDIUM -> 4
    DifficultyType.HARD -> if (isPortrait) 5 else 6
  }

  if (dialogState.show)  {
    Dialog(
      type = dialogState.type,
      title = dialogState.title,
      desc = dialogState.message,
      onDismiss = dialogState.onDismiss,
      onOk = dialogState.onOk,
    )
  }

  Game(
    isPortrait = isPortrait,
    cells = cells,
    movesNum = moves,
    remainingPairs = remainingPairs,
    valueTimer = valueTimer,
    cards = cards,
    onItemSelected = viewModel::onSelectedCard,
    onBackClicked = viewModel::exitGame
  )
}