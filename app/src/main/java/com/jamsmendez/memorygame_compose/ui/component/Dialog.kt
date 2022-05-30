package com.jamsmendez.memorygame_compose.ui.component

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import com.jamsmendez.memorygame_compose.util.DialogType

@Composable
fun Dialog(
  type: DialogType,
  title: String,
  desc: String,
  onDismiss: () -> Unit,
  onOk: () -> Unit,
) {
  MaterialTheme {
    when (type) {
      DialogType.SUCCESS -> {
        SuccessDialog(
          title = title,
          desc = desc,
          onDismiss = onDismiss,
          onOk = onOk
        )
      }
      DialogType.ERROR -> {
        ErrorDialog(
          title = title,
          desc = desc,
          onDismiss = onDismiss,
          onOk = onOk
        )
      }
      DialogType.INFO -> {
        InfoDialog(
          title = title,
          desc = desc,
          onDismiss = onDismiss,
          onOk = onOk
        )
      }
    }
  }
}

