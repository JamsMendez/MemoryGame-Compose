package com.jamsmendez.memorygame_compose.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.jamsmendez.memorygame_compose.R
import com.jamsmendez.memorygame_compose.ui.theme.HaloTypography

@Composable
fun Title(
  modifier: Modifier = Modifier
) {
  Row(
    modifier = modifier
      .fillMaxWidth(),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.Center
  ) {
    Text(
      text = "MemoryGame",
      modifier = Modifier,
      Color.White,
      style = HaloTypography.h5
    )
    Text(
      text = "Halo",
      modifier = Modifier,
      color = Color.White,
      fontSize = 56.sp,
      fontWeight = FontWeight.Bold,
      fontFamily = FontFamily(
        Font(R.font.halo),
        Font(R.font.halo, FontWeight.Bold)
      )
    )
  }
}