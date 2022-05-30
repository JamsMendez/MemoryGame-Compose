package com.jamsmendez.memorygame_compose.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jamsmendez.memorygame_compose.R
import com.jamsmendez.memorygame_compose.ui.theme.MemoryGameComposeTheme
import com.jamsmendez.memorygame_compose.util.DifficultyType

@Composable
fun Welcome(
  modifier: Modifier = Modifier,
  onSelected: (value: DifficultyType) -> Unit = {}
) {
  Box {
    Image(
      painter = painterResource(id = R.drawable.background_memory_game),
      contentDescription = "Image background",
      modifier = Modifier
        .fillMaxSize(),
      contentScale = ContentScale.FillBounds
    )
    Column(
      modifier = modifier
        .background(Color.Black.copy(alpha = 0.5f))
        .fillMaxSize(),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center
    ) {
      Title()
      Spacer(
        modifier = Modifier
          .fillMaxWidth()
          .height(16.dp)
      )
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 16.dp)
      ) {
        SimpleButton(
          modifier = Modifier.weight(0.3f),
          text = "Easy",
          onClick = { onSelected(DifficultyType.EASY) }
        )
        Spacer(
          modifier = Modifier
            .height(16.dp)
            .weight(0.05f)
        )
        SimpleButton(
          modifier = Modifier.weight(0.3f),
          text = "Medium",
          onClick = { onSelected(DifficultyType.MEDIUM) }
        )
        Spacer(
          modifier = Modifier
            .height(16.dp)
            .weight(0.05f)
        )
        SimpleButton(
          modifier = Modifier.weight(0.3f),
          text = "Hard",
          onClick = { onSelected(DifficultyType.HARD) }
        )
      }
    }
  }
}

@Composable
@Preview(showBackground = true)
fun WelcomePreview() {
  MemoryGameComposeTheme {
    Surface(
      modifier = Modifier.fillMaxSize(),
      color = MaterialTheme.colors.background
    ) {
      Welcome()
    }
  }
}