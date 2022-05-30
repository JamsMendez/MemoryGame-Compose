package com.jamsmendez.memorygame_compose.util

import javax.inject.Singleton

enum class DialogType {
  SUCCESS, ERROR, INFO
}

@Singleton
enum class DifficultyType {
  EASY,
  MEDIUM,
  HARD
}