package com.jamsmendez.memorygame_compose.ui.viewmodel

import com.jamsmendez.memorygame_compose.util.DifficultyType

data class BoardState(
  val cardList: List<BoardCard> = listOf(),
  val moves: Int = 0,
  val remainingPairs: Int = 0,
  val difficulty: DifficultyType = DifficultyType.EASY
)

