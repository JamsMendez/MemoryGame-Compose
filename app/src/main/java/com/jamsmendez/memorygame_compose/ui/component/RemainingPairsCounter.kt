package com.jamsmendez.memorygame_compose.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jamsmendez.memorygame_compose.ui.theme.HaloTypography

@Composable
fun RemainingPairsCounter(
  modifier: Modifier = Modifier,
  value: Int = 0) {
  Column(
    modifier = modifier.fillMaxWidth(),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    BorderBox(
      modifier = Modifier.size(48.dp, 36.dp),
    ) {
      Text(
        text = "$value",
        modifier = Modifier,
        Color.White,
        style = HaloTypography.h6
      )
    }
    Text(
      text = "Remaining",
      modifier = Modifier,
      Color.White,
      style = HaloTypography.overline
    )
  }

}