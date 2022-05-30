package com.jamsmendez.memorygame_compose.ui.viewmodel

import android.content.Context
import android.media.MediaPlayer
import android.os.CountDownTimer
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jamsmendez.memorygame_compose.R
import com.jamsmendez.memorygame_compose.navigation.RouteNavigator
import com.jamsmendez.memorygame_compose.ui.screen.GameRoute
import com.jamsmendez.memorygame_compose.util.DialogType
import com.jamsmendez.memorygame_compose.util.DifficultyType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel
@Inject constructor(
  private val context: Context,
  private val routerNavigator: RouteNavigator,
  savedStateHandle: SavedStateHandle,
) : ViewModel(), RouteNavigator by routerNavigator {

  private val TIMER_TOTAL = 60
  private var isRunning = false

  private val difficulty = DifficultyType.valueOf(GameRoute.getDifficulty(savedStateHandle))

  private val _timerDownState: MutableState<Float> = mutableStateOf(0f)
  private val _boardState: MutableState<BoardState> = mutableStateOf(BoardState())
  private val _dialogState: MutableState<DialogState> = mutableStateOf(DialogState())

  val timerDownState: State<Float> = _timerDownState
  val boardState: State<BoardState> = _boardState
  val dialogState: State<DialogState> = _dialogState

  private val board: Board = Board(_boardState.value)

  private var _mediaShields: MediaPlayer = MediaPlayer.create(context, R.raw.shields)
  private var _mediaLowShields: MediaPlayer = MediaPlayer.create(context, R.raw.low_shields)

  private val countTimer = object : CountDownTimer((1000 * TIMER_TOTAL).toLong(), 1000) {
    override fun onTick(millisUntilFinished: Long) {
      val points = (millisUntilFinished / 1000)

      val value = points.toFloat() / TIMER_TOTAL
      _timerDownState.value = value

      if (value <= 0.3f && !_mediaLowShields.isPlaying) _mediaLowShields.start()
    }

    override fun onFinish() {
      board.lock()
      blockCards()
      showDialogTimeOver()

      restartLowShieldsSound()
    }
  }

  init {
    resetGame()
  }

  private fun showDialogTimeOver() {
    _dialogState.value = DialogState(
      "Time is over!",
      "Want to play again?",
      type = DialogType.ERROR,
      show = true,
      onDismiss = { dismissDialogs() },
      onOk = {
        resetGame()
        dismissDialogs()
      }
    )
  }

  private fun showDialogSuccess() {
    countTimer.cancel()

    _dialogState.value = DialogState(
      "Congratulations!",
      "Want to play again?",
      type = DialogType.SUCCESS,
      show = true,
      onDismiss = { dismissDialogs() },
      onOk = {
        resetGame()
        dismissDialogs()
      }
    )
  }

  private fun showDialogExit() {
    _dialogState.value = DialogState(
      "Exit",
      "Want to exit?",
      type = DialogType.INFO,
      show = true,
      onDismiss = { dismissDialogs() },
      onOk = {
        countTimer.cancel()
        restartLowShieldsSound()
        restartShieldsSound()

        dismissDialogs()
        popBackStack()
      }
    )
  }

  private fun dismissDialogs() {
    _dialogState.value = DialogState(show = false)
  }

  private fun blockCards() {
    var cardList = _boardState.value.cardList
    val moves = _boardState.value.moves
    val remainingPairs = _boardState.value.remainingPairs

    cardList = cardList.map { card -> card.copy(blocked = true) }

    _boardState.value = BoardState(
      cardList = cardList,
      moves = moves,
      remainingPairs = remainingPairs,
      difficulty = difficulty
    )
  }

  private fun distributeCards() {
    val cardList = mutableListOf<BoardCard>()

    val images: List<String> = mutableListOf(
      "https://i.postimg.cc/gLkPfGXb/pngwing-com.png",
      "https://i.postimg.cc/Z0TZ9xfs/pngwing-com-2.png",
      "https://i.postimg.cc/7bf4MZkS/pngwing-com-1.png",
      "https://i.postimg.cc/YhYJb4yL/pngwing-com-10.png",
      "https://i.postimg.cc/WqJQRtNK/pngwing-com-11.png",
      "https://i.postimg.cc/JD0fqP9R/pngwing-com-12.png",
      "https://i.postimg.cc/NydhmWCN/pngwing-com-13.png",
      "https://i.postimg.cc/sxD1JbXW/pngwing-com-14.png",
      "https://i.postimg.cc/Xp50m1XJ/pngwing-com-15.png",
      "https://i.postimg.cc/5QYMxn3s/pngwing-com-16.png",
      "https://i.postimg.cc/grX9LtVD/pngwing-com-4.png",
      "https://i.postimg.cc/BLw09dx8/pngwing-com-5.png",
      "https://i.postimg.cc/gwsp4wjH/pngwing-com-6.png",
      "https://i.postimg.cc/06PxpGkN/pngwing-com-7.png",
      "https://i.postimg.cc/d7tps5QG/pngwing-com-9.png",
    ).shuffled()

    val size = when (difficulty) {
      DifficultyType.EASY -> 16
      DifficultyType.MEDIUM -> 24
      DifficultyType.HARD -> 30
    }

    val limit = (size / 2) - 1
    for (i in 0..limit) {
      val image = images[i]
      cardList.add(BoardCard(value = i, image = image))
      cardList.add(BoardCard(value = i, image = image))
    }

    _boardState.value = BoardState(
      cardList = cardList.subList(0, size).shuffled(),
      moves = 0,
      remainingPairs = cardList.size / 2,
      difficulty = difficulty,
    )

    board.unlock()
  }

  private fun resetGame() {
    distributeCards()

    countTimer.cancel()

    restartLowShieldsSound()

    viewModelScope.launch {
      _timerDownState.value = 1f
      isRunning = false

      _mediaShields.start()

      val timiMillis: Long = (1000 * 2).toLong()
      delay(timeMillis = timiMillis)

      restartShieldsSound()
    }
  }

  fun exitGame() {
    showDialogExit()
  }

  private fun restartShieldsSound() {
    if (_mediaShields.isPlaying) _mediaShields.stop()
    _mediaShields = MediaPlayer.create(context, R.raw.shields)
  }

  private fun restartLowShieldsSound() {
    if (_mediaLowShields.isPlaying) _mediaLowShields.stop()
    _mediaLowShields = MediaPlayer.create(context, R.raw.low_shields)
  }


  fun onSelectedCard(index: Int) {
    board.setState(_boardState.value)
    board.selectedCard(index)

    _boardState.value = board.getState()

    viewModelScope.launch {
      if (board.hasTwoCardsUp()) delay(500)

      board.validatePairs()

      _boardState.value = board.getState()

      if (board.finished()) showDialogSuccess()
    }

    if (!isRunning) {
      isRunning = true
      countTimer.start()
    }
  }
}