package com.example.ecosplash

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.example.ecosplash.classes.Sombrero

//iglu 20
// playa 25
//piña 35
//moyai 100
@Composable
    fun backgrounds(backgroundImages: List<Painter> = backgroundsOptions()): List<Sombrero> {
    var imagenes = listOf(
        Sombrero(nombre = "Sin accesorio", precio = 0, imagen = backgroundImages[0],descripcion = "No lo sé, solo estaba nadando por hawai, alguien se me acerco con uno de estos y me dijo 'MONDONGO', y se alejó lentamente .",frames = fisbowlanimated(),desbloqueado = true),
        Sombrero(nombre = "moyai", precio = 150, imagen = backgroundImages[1],descripcion = "No lo sé, solo estaba nadando por hawai, alguien se me acerco con uno de estos y me dijo 'MONDONGO', y se alejó lentamente .",frames = fisbowlMoyaiAnimated(),desbloqueado = false),
        Sombrero(nombre = "palmera", precio = 150, imagen = backgroundImages[2],descripcion = "No lo sé, solo estaba nadando por hawai, alguien se me acerco con uno de estos y me dijo 'MONDONGO', y se alejó lentamente .",frames = fisbowlPlayaAnimated(),desbloqueado = false),
        Sombrero(nombre = "iglu", precio = 150, imagen = backgroundImages[3],descripcion = "No lo sé, solo estaba nadando por hawai, alguien se me acerco con uno de estos y me dijo 'MONDONGO', y se alejó lentamente .",frames = fisbowlIgluAnimated(),desbloqueado = false),
       // Sombrero(nombre = "piña", precio = 150, imagen = backgroundImages[4],descripcion = "No lo sé, solo estaba nadando por hawai, alguien se me acerco con uno de estos y me dijo 'MONDONGO', y se alejó lentamente .",desbloqueado = false)
    )
    return imagenes
}
//flor 15
//mexicano 20
// lentes 45
// vikingo 50
@Composable
fun hats(hatImages: List<Painter> = hatOptions()): List<Sombrero> {
    var imagenes = listOf(
        Sombrero(nombre = "Sin accesorio", precio = 0, imagen = hatImages[0],descripcion = "No lo sé, solo estaba nadando por hawai, alguien se me acerco con uno de estos y me dijo 'MONDONGO', y se alejó lentamente .",frames = ajoAnimated(),desbloqueado = true),
        Sombrero(nombre = "vikingo", precio = 150, imagen = hatImages[1],descripcion = "No lo sé, solo estaba nadando por hawai, alguien se me acerco con uno de estos y me dijo 'MONDONGO', y se alejó lentamente .",frames = ajoVikingoAnimated(),desbloqueado = false),
        Sombrero(nombre = "lentes", precio = 150, imagen = hatImages[2],descripcion = "No lo sé, solo estaba nadando por hawai, alguien se me acerco con uno de estos y me dijo 'MONDONGO', y se alejó lentamente .",frames = ajoLentesAnimated(),desbloqueado = false),
        Sombrero(nombre = "mexicano", precio = 150, imagen = hatImages[3],descripcion = "No lo sé, solo estaba nadando por hawai, alguien se me acerco con uno de estos y me dijo 'MONDONGO', y se alejó lentamente .",frames = ajoMexAnimated(),desbloqueado = false),
         Sombrero(nombre = "flor", precio = 150, imagen = hatImages[4],descripcion = "No lo sé, solo estaba nadando por hawai, alguien se me acerco con uno de estos y me dijo 'MONDONGO', y se alejó lentamente .",frames =  ajoFlorAnimated(),desbloqueado = false)
    )
    return imagenes
}