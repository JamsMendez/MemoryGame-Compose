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

    lockProcess()

    var cardList = state.cardList

    val selectedCard = cardList[index].copy(index = index, isOpen = true, enabled = false)

    if (firstCard.isOpen) {
      secondCard = selectedCard

      cardList = state.cardList.mapIndexed { i, card ->
        if (i == index) selectedCard else card.copy(enabled = false)
      }

    } else {
      firstCard = selectedCard

      cardList = state.cardList.mapIndexed { i, card ->
        if (i == index) selectedCard else card
      }
    }

    state = state.copy(cardList = cardList)
  }

  fun disabledAllCards() {
    var cardList = state.cardList
    cardList = cardList.map { card -> card.copy(enabled = false) }
    state = state.copy(cardList = cardList)
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
            enabled = true) else card.copy(enabled = true)
        }

      } else {
        cardList = cardList.mapIndexed { i, card ->
          if (i == firstCard.index || i == secondCard.index) card.copy(isOpen = false,
            enabled = true) else card.copy(enabled = true)
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

    unlockProcess()
  }

  fun hasTwoCardsUp(): Boolean = firstCard.isOpen && secondCard.isOpen

  fun finished(): Boolean = state.remainingPairs == 0

  fun lockProcess() {
    processing = true
  }

  fun unlockProcess() {
    processing = false
  }

  fun getState(): BoardState {
    return state
  }

  fun setState(state: BoardState) {
    this.state = state
  }
}