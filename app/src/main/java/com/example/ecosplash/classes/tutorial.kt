package com.example.ecosplash.classes

import androidx.compose.ui.graphics.painter.Painter

data class Tutorial(
    val firstText: String,
    val imageSize: Float,
    val textSize: Float,
    val image: Painter,
    val secondText: String,
    val top: Float,
    val bottom: Float

)