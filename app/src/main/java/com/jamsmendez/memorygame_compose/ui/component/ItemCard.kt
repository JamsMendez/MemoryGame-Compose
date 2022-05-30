package com.jamsmendez.memorygame_compose.ui.component

import android.content.res.Resources
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.jamsmendez.memorygame_compose.R
import com.jamsmendez.memorygame_compose.ui.theme.MemoryGameComposeTheme

fun pxToDp(px: Int): Int {
  return (px / Resources.getSystem().displayMetrics.density).toInt()
}

@Composable
fun ItemCard(
  value: Int = 0,
  image: String = "",
  isOpen: Boolean = false,
  blocked: Boolean = false,
  onSelected: () -> Unit = {},
  boardSize: Dp = 0.dp
) {
  val painter = rememberAsyncImagePainter(
    model = ImageRequest.Builder(LocalContext.current)
      .data(image)
      .size(Size.ORIGINAL)
      .crossfade(true)
      .build()
  )

  Box(
    modifier = Modifier.padding(2.dp)
  ) {
    Card(
      modifier = Modifier
        .fillMaxWidth()
        .height(boardSize)
        .clickable(
          enabled = !blocked,
          onClick = onSelected
        ),
      backgroundColor = Color.Gray
    ) {
      if (isOpen) {
        Image(
          painter = painter,
          contentDescription = "",
          contentScale = ContentScale.Inside
        )
      } else {
        Image(
          painter = painterResource(id = R.drawable.halo_card),
          contentDescription = "",
          contentScale = ContentScale.Inside
        )
      }
    }
  }
}

@Preview
@Composable
fun ItemCardPreview() {
  MemoryGameComposeTheme {
    ItemCard()
  }
}

