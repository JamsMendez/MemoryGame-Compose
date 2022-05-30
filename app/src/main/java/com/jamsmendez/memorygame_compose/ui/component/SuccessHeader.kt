package com.jamsmendez.memorygame_compose.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.jamsmendez.memorygame_compose.R

@Composable
fun SuccessHeader(modifier: Modifier) {
  val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.success))
  val progress by animateLottieCompositionAsState(composition = composition)

  LottieAnimation(
    composition = composition,
    progress = progress,
    modifier = modifier
  )
}