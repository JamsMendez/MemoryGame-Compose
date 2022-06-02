package com.jamsmendez.memorygame_compose.util

enum class CardFace(val angle: Float) {
  Front(0f) {
    override val next: CardFace
      get() = Back
  },
  Back(180f) {
    override val next: CardFace
      get() = Front
  };

  abstract val next: CardFace
}