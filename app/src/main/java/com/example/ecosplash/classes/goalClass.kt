package com.example.ecosplash.classes

import androidx.compose.ui.graphics.painter.Painter

data class goal(
    val nombre: String,
    val progress: Int,
    val imagen: Painter
)