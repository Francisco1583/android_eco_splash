package com.example.ecosplash.classes

import androidx.compose.ui.graphics.painter.Painter

data class sombrero(
    val nombre: String,
    val precio: Int,
    val imagen: Painter,
    val musica: String,
    var desbloqueado: Boolean
)

//data class escenario(
  //  val nombre: String,
    //val precio: Int,
    //val imagen: Painter,
    //val musica: String
//)