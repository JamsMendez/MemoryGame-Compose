package com.jamsmendez.memorygame_compose.ui.viewmodel

import com.jamsmendez.memorygame_compose.util.DialogType

data class DialogState(
  val title: String = "",
  val message: String = "",
  val type: DialogType = DialogType.INFO,
  val show: Boolean = false,
  val onDismiss: () -> Unit = {},
  val onOk: () -> Unit = {},
)