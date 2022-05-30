package com.jamsmendez.memorygame_compose.ui.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.jamsmendez.memorygame_compose.navigation.SetupNavGraph
import com.jamsmendez.memorygame_compose.ui.theme.MemoryGameComposeTheme
import dagger.hilt.android.AndroidEntryPoint
import com.jamsmendez.memorygame_compose.R

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      setTheme(R.style.Theme_MemoryGameCompose)
      MemoryGameComposeTheme {
        Surface(
          modifier = Modifier.fillMaxSize(),
          color = MaterialTheme.colors.background
        ) {
          val navController = rememberNavController()
          SetupNavGraph(navController = navController)
        }
      }
    }
  }
}