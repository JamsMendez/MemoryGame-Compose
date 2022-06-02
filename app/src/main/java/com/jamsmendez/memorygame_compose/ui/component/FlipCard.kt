package com.jamsmendez.memorygame_compose.ui.component

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.jamsmendez.memorygame_compose.util.CardFace


@Composable
fun FlipCard(
  modifier: Modifier = Modifier,
  cardFace: CardFace,
  back: @Composable () -> Unit = {},
  front: @Composable () -> Unit = {}
) {
  val rotation = animateFloatAsState(
    targetValue = cardFace.angle,
    animationSpec = tween(
      durationMillis = 400,
      easing = FastOutSlowInEasing,
    )
  )

  Box(
    modifier = Modifier.padding(2.dp)
  ) {
    Card(
      modifier = modifier
        .graphicsLayer {
          rotationY  = rotation.value
        },
      backgroundColor = Color.Gray
    ) {
      if (rotation.value <= 90f) {
       front()
      } else {
        back()
      }
    }
  }
}