package com.example.ecosplash.classes

import androidx.compose.ui.graphics.painter.Painter

data class Sombrero(
    val nombre: String,
    val precio: Int,
    val imagen: Painter,
    val descripcion: String,
    val frames: List<Painter>,
    val halframes: List<Painter>,
    val emptyFrames: List<Painter>,
    var desbloqueado: Boolean
)