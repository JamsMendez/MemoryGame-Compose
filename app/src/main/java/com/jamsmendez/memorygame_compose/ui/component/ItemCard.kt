package com.jamsmendez.memorygame_compose.ui.component


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.jamsmendez.memorygame_compose.R
import com.jamsmendez.memorygame_compose.ui.theme.MemoryGameComposeTheme
import com.jamsmendez.memorygame_compose.util.CardFace

@Composable
fun ItemCard(
  image: String = "",
  isOpen: Boolean = false,
  blocked: Boolean = false,
  onSelected: () -> Unit = {},
  height: Dp = 0.dp
) {
  val painter = rememberAsyncImagePainter(
    model = ImageRequest.Builder(LocalContext.current)
      .data(image)
      .size(Size.ORIGINAL)
      .crossfade(true)
      .build()
  )

  val cardFace = if (isOpen) CardFace.Front else CardFace.Back

  FlipCard(
    modifier = Modifier
      .fillMaxWidth()
      .height(height)
      .clickable(
        enabled = !blocked,
        onClick = onSelected
      ),
    cardFace = cardFace,
    front = {
      Image(
        painter = painter,
        contentDescription = "Front image",
        contentScale = ContentScale.Inside
      )
    },
    back = {
      Image(
        painter = painterResource(id = R.drawable.halo_card),
        contentDescription = "Back image",
        contentScale = ContentScale.Inside
      )
    }
  )
}

@Preview
@Composable
fun ItemCardPreview() {
  MemoryGameComposeTheme {
    ItemCard()
  }
}

