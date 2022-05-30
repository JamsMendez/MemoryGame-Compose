package com.jamsmendez.memorygame_compose.ui.component

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.jamsmendez.memorygame_compose.R
import com.jamsmendez.memorygame_compose.ui.theme.MemoryGameComposeTheme
import com.jamsmendez.memorygame_compose.ui.viewmodel.BoardCard

@Composable
fun Game(
  isPortrait: Boolean = true,
  cells: Int = 4,
  movesNum: Int = 0,
  remainingPairs: Int = 0,
  valueTimer: Float = 0.0f,
  cards: List<BoardCard> = emptyList(),
  onItemSelected: (index: Int) -> Unit = {},
  onBackClicked: () -> Unit = {}
) {
  Box(modifier = Modifier) {
    Image(
      painter = painterResource(id = R.drawable.background_memory_game),
      contentDescription = "Image background",
      modifier = Modifier
        .fillMaxSize(),
      contentScale = ContentScale.FillBounds
    )
  }
  Column(
    modifier = Modifier.background(Color.Black.copy(alpha = 0.5f)),
  ) {
    TopAppBar(
      title = {
        Text(
          text = "MemoryGame",
          modifier = Modifier
            .fillMaxWidth()
            .padding(end = 64.dp),
          color = Color.White,
          textAlign = TextAlign.Center
        ) },
      modifier = Modifier
        .background(Color.Transparent),
      navigationIcon = {
        IconButton(onClick = onBackClicked) {
          Icon(
            Icons.Filled.ArrowBack,
            contentDescription = null,
            tint = Color.White
          )
        }
      },
      backgroundColor = Color.Transparent,
      elevation = 0.dp,
    )
    Row(
      modifier = Modifier,
      horizontalArrangement = Arrangement.Center,
      verticalAlignment = Alignment.CenterVertically
    ) {
      MoveCounter(
        Modifier.weight(0.2f),
        value = movesNum
      )
      TimerDown(
        modifier = Modifier.weight(0.6f),
        value = valueTimer
      )
      RemainingPairsCounter(
        Modifier.weight(0.2f),
        value = remainingPairs
      )
    }
    Board(
      modifier = Modifier.weight(1f),
      cells = cells,
      cards = cards,
      onItemSelected = onItemSelected
    )
  }
}

@Preview
@Composable
fun GamePreview() {
  MemoryGameComposeTheme {
    Surface(
      modifier = Modifier.fillMaxSize(),
      color = MaterialTheme.colors.background
    ) {
      Game()
    }
  }
}