package com.example.ecosplash.classes

import androidx.compose.ui.graphics.painter.Painter

data class Achievements(
    val nombre: String,
    val id: Int,
    val imagen: Painter,
    val descripcion: String,
    val goal: Int,
    var progress: Int,
    var completed: Boolean
)