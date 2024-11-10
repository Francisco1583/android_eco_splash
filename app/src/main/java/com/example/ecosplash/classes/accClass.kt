package com.example.ecosplash.classes

import androidx.compose.ui.graphics.painter.Painter

data class Sombrero(
    val nombre: String,
    val precio: Int,
    val imagen: Painter,
    //val musica: String,
    val descripcion: String,
    val frames: List<Painter>,
    var desbloqueado: Boolean
)

//data class escenario(
  //  val nombre: String,
    //val precio: Int,
    //val imagen: Painter,
    //val musica: String
//)