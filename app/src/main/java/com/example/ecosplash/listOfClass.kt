package com.example.ecosplash

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.example.ecosplash.classes.Achievements
import com.example.ecosplash.classes.Sombrero

@Composable
    fun backgrounds(backgroundImages: List<Painter> = backgroundsOptions()): List<Sombrero> {
    var imagenes = listOf(
        Sombrero(nombre = "Sin accesorio", precio = 0, imagen = backgroundImages[0],descripcion = "No lo sé, solo estaba nadando por hawai, alguien se me acerco con uno de estos y me dijo 'MONDONGO', y se alejó lentamente .",frames = fisbowlanimated(), halframes = fisbowlMIdanimated(), emptyFrames = fisbowlEmptyanimated(),desbloqueado = true),
        Sombrero(nombre = "Iglu", precio = 15, imagen = backgroundImages[3],descripcion = "No lo sé, solo estaba nadando por hawai, alguien se me acerco con uno de estos y me dijo 'MONDONGO', y se alejó lentamente .",frames = fisbowlIgluAnimated(), halframes = fisbowlIgluMidAnimated(), emptyFrames = fisbowlIgluEmptyAnimated(),desbloqueado = false),
        Sombrero(nombre = "Palmera", precio = 20, imagen = backgroundImages[2],descripcion = "No lo sé, solo estaba nadando por hawai, alguien se me acerco con uno de estos y me dijo 'MONDONGO', y se alejó lentamente .",frames = fisbowlPlayaAnimated(), halframes =fisbowlMidPlayaAnimated(), emptyFrames = fishBowlEmptyPlaya(),desbloqueado = false),
        Sombrero(nombre = "Piña", precio = 30, imagen = backgroundImages[4],descripcion = "No lo sé, solo estaba nadando por hawai, alguien se me acerco con uno de estos y me dijo 'MONDONGO', y se alejó lentamente .",frames = fishBowlPineapple(),halframes= fishBowlMidPineapple(), emptyFrames = fishBowlEmptyPineapple()  ,desbloqueado = false),
        Sombrero(nombre = "Moyai", precio = 100, imagen = backgroundImages[1],descripcion = "No lo sé, solo estaba nadando por hawai, alguien se me acerco con uno de estos y me dijo 'MONDONGO', y se alejó lentamente .",frames = fisbowlMoyaiAnimated(), halframes = fisbowlMoyaiMidAnimated(), emptyFrames = fisbowlMoyaiEmptyAnimated(),desbloqueado = false)
    )
    return imagenes
}

@Composable
fun hats(hatImages: List<Painter> = hatOptions()): List<Sombrero> {
    var imagenes = listOf(
        Sombrero(nombre = "Sin accesorio", precio = 0, imagen = hatImages[0],descripcion = "No lo sé, solo estaba nadando por hawai, alguien se me acerco con uno de estos y me dijo 'MONDONGO', y se alejó lentamente .",frames = ajoAnimated(), halframes = ajoSadAnimated(), emptyFrames = ajoVerySadAnimated(),desbloqueado = true),
        Sombrero(nombre = "Flor", precio = 20, imagen = hatImages[4],descripcion = "No lo sé, solo estaba nadando por hawai, alguien se me acerco con uno de estos y me dijo 'MONDONGO', y se alejó lentamente .",frames =  ajoFlorAnimated(), halframes = ajoFlorSadAnimated(), emptyFrames = ajoFlorVerySadAnimated(),desbloqueado = false),
        Sombrero(nombre = "Mexicano", precio = 25, imagen = hatImages[3],descripcion = "No lo sé, solo estaba nadando por hawai, alguien se me acerco con uno de estos y me dijo 'MONDONGO', y se alejó lentamente .",frames = ajoMexAnimated(), halframes = ajoMexSadAnimated(), emptyFrames = ajoMexVerySadAnimated(),desbloqueado = false),
        Sombrero(nombre = "Lentes", precio = 45, imagen = hatImages[2],descripcion = "No lo sé, solo estaba nadando por hawai, alguien se me acerco con uno de estos y me dijo 'MONDONGO', y se alejó lentamente .",frames = ajoLentesAnimated(), halframes = ajoLentesSadAnimated(), emptyFrames = ajoLentesVerySadAnimated(),desbloqueado = false),
        Sombrero(nombre = "Vikingo", precio = 100, imagen = hatImages[1],descripcion = "No lo sé, solo estaba nadando por hawai, alguien se me acerco con uno de estos y me dijo 'MONDONGO', y se alejó lentamente .",frames = ajoVikingoAnimated(), halframes = ajoVikingoSadAnimated(), emptyFrames = ajoVikingoVerySadAnimated(),desbloqueado = false),
    )
    return imagenes
}

@Composable
fun achivements(achivemntsImages: List<Painter> = achivemntsImages()): List<Achievements> {
    var imagenes = listOf(
        Achievements(nombre = "ducha rapida",id = 0, imagen =achivemntsImages[0], descripcion = "Toma 30 duchas rapidas", goal = 30, progress = 0, completed = false),
        Achievements(nombre = "super ahorrador",id = 1, imagen =achivemntsImages[1], descripcion = "Ahorra más de 10,000 litros de agua", goal = 10000, progress = 0, completed = false),
        Achievements(nombre = "ahorro semanal",id = 2, imagen =achivemntsImages[2], descripcion = "Compra todos los objetos de la tienda", goal = 8, progress = 0, completed = false)
    )
    return imagenes
}