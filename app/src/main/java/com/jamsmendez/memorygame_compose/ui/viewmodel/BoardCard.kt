package com.jamsmendez.memorygame_compose.ui.viewmodel

data class BoardCard(
  val value: Int = 0,
  val index: Int = -1,
  val image: String = "",
  val isOpen: Boolean = false,
  val blocked: Boolean = false
)