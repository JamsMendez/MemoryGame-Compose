package com.jamsmendez.memorygame_compose.ui.viewmodel

import kotlinx.coroutines.delay

class Board(
  private var state: BoardState
) {
  private var firstCard: BoardCard = BoardCard()
  private var secondCard: BoardCard = BoardCard()
  private var processing: Boolean = false

  fun selectedCard(index: Int) {
    if (processing) return

    lock()

    var cardList = state.cardList
    val moves = state.moves
    val remainingPairs = state.remainingPairs
    val difficulty = state.difficulty

    val selectedCard = cardList[index].copy(index = index, isOpen = true, blocked = true)

    if (firstCard.isOpen) {
      secondCard = selectedCard

      cardList = state.cardList.mapIndexed { i, card ->
        if (i == index) selectedCard else card.copy(blocked = true)
      }

    } else {
      firstCard = selectedCard

      cardList = state.cardList.mapIndexed { i, card ->
        if (i == index) selectedCard else card
      }
    }

    state = BoardState(
      cardList = cardList,
      moves = moves,
      remainingPairs = remainingPairs,
      difficulty = difficulty
    )
  }

  fun validatePairs() {
    var cardList = state.cardList
    var moves = state.moves
    var remainingPairs = state.remainingPairs
    val difficulty = state.difficulty

    if (hasTwoCardsUp()) {
      if (firstCard.value == secondCard.value) {
        remainingPairs -= 1

        firstCard = BoardCard()
        secondCard = BoardCard()

        cardList = cardList.mapIndexed { i, card ->
          if (i == firstCard.index || i == secondCard.index) card.copy(isOpen = true,
            blocked = false) else card.copy(blocked = false)
        }

      } else {
        cardList = cardList.mapIndexed { i, card ->
          if (i == firstCard.index || i == secondCard.index) card.copy(isOpen = false,
            blocked = false) else card.copy(blocked = false)
        }

        firstCard = BoardCard()
        secondCard = BoardCard()
      }

      moves += 1

      state = BoardState(
        cardList = cardList.toList(),
        moves = moves,
        remainingPairs = remainingPairs,
        difficulty = difficulty
      )
    }

    unlock()
  }

  fun hasTwoCardsUp(): Boolean = firstCard.isOpen && secondCard.isOpen

  fun finished(): Boolean = state.remainingPairs == 0

  fun getState(): BoardState {
    return state
  }

  fun setState(state: BoardState) {
    this.state = state
  }

  fun lock() {
    processing = true
  }

  fun unlock() {
    processing = false
  }
}