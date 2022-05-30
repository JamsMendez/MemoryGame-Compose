package com.jamsmendez.memorygame_compose.ui.component

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jamsmendez.memorygame_compose.ui.theme.MemoryGameComposeTheme
import com.jamsmendez.memorygame_compose.ui.viewmodel.BoardCard



@Composable
fun Board(
  modifier: Modifier = Modifier,
  cells: Int = 4,
  cards: List<BoardCard> = emptyList(),
  onItemSelected: (index: Int) -> Unit = {},
) {
  val cardsNum = cards.size / cells

  val configuration = LocalConfiguration.current
  val screenHeight = configuration.screenHeightDp.dp
  val heightItem = ((screenHeight - 120.dp) / cardsNum) - (2 * (cardsNum - 1)).dp

  LazyVerticalGrid(
    columns = GridCells.Fixed(cells),
    modifier = modifier
      .fillMaxWidth(),
    contentPadding = PaddingValues(8.dp),
    verticalArrangement = Arrangement.Center,
    reverseLayout = true,
  ) {
    itemsIndexed(cards) { index, card ->
      ItemCard(
        value = card.value,
        image = card.image,
        isOpen = card.isOpen,
        blocked = card.blocked,
        onSelected = {
          onItemSelected(index)
        },
        heightItem
      )
    }
  }
}

@Preview
@Composable
fun TablePreview() {
  MemoryGameComposeTheme {
    Board()
  }
}