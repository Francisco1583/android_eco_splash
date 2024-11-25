package com.example.ecosplash

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.example.ecosplash.classes.Achievements
import com.example.ecosplash.classes.Sombrero
import com.example.ecosplash.classes.Tutorial

@Composable
    fun backgrounds(backgroundImages: List<Painter> = backgroundsOptions()): List<Sombrero> {
    val imagenes = listOf(
        Sombrero(nombre = "Sin accesorio", precio = 0, imagen = backgroundImages[0],descripcion = "Nada por aquí, nada por allá. Un espacio vacío que invita a soñar con lo que podría estar.",frames = fisbowlanimated(), halframes = fisbowlMIdanimated(), emptyFrames = fisbowlEmptyanimated(),desbloqueado = true),
        Sombrero(nombre = "Iglu", precio = 15, imagen = backgroundImages[3],descripcion = "Un pingüino lo dejó aquí y dijo: 'Frío en el agua, cálido en casa'. Luego se deslizó hacia el horizonte.",frames = fisbowlIgluAnimated(), halframes = fisbowlIgluMidAnimated(), emptyFrames = fisbowlIgluEmptyAnimated(),desbloqueado = false),
        Sombrero(nombre = "Palmera", precio = 20, imagen = backgroundImages[2],descripcion = "Una pequeña isla con palmeras apareció de la nada. Se dice que quien la encuentra, tendrá siempre cocos frescos.",frames = fisbowlPlayaAnimated(), halframes =fisbowlMidPlayaAnimated(), emptyFrames = fishBowlEmptyPlaya(),desbloqueado = false),
        Sombrero(nombre = "Piña", precio = 30, imagen = backgroundImages[4],descripcion = "Una piña gigante flotó hasta aquí después de una tormenta. Alguien la convirtió en una casa, ¡y huele increíble por dentro!",frames = fishBowlPineapple(),halframes= fishBowlMidPineapple(), emptyFrames = fishBowlEmptyPineapple()  ,desbloqueado = false),
        Sombrero(nombre = "Moyai", precio = 100, imagen = backgroundImages[1],descripcion = "No lo sé, solo estaba nadando por hawai, alguien se me acerco con uno de estos y me dijo 'MONDONGO', y se alejó lentamente.",frames = fisbowlMoyaiAnimated(), halframes = fisbowlMoyaiMidAnimated(), emptyFrames = fisbowlMoyaiEmptyAnimated(),desbloqueado = false)
    )
    return imagenes
}

@Composable
fun hats(hatImages: List<Painter> = hatOptions()): List<Sombrero> {
    val imagenes = listOf(
        Sombrero(nombre = "Sin accesorio", precio = 0, imagen = hatImages[0],descripcion = "Sin adornos, sin distracciones. Este ajolote luce su belleza natural, tal y como es.",frames = ajoAnimated(), halframes = ajoSadAnimated(), emptyFrames = ajoVerySadAnimated(),desbloqueado = true),
        Sombrero(nombre = "Flor", precio = 20, imagen = hatImages[4],descripcion = "Se dice que esta flor flota en busca de alguien especial. Al parecer, ha elegido a tu ajolote.",frames =  ajoFlorAnimated(), halframes = ajoFlorSadAnimated(), emptyFrames = ajoFlorVerySadAnimated(),desbloqueado = false),
        Sombrero(nombre = "Mexicano", precio = 25, imagen = hatImages[3],descripcion = "Encontrado en una fiesta submarina. Desde entonces, tu ajolote no puede resistirse a bailar al ritmo de mariachis.",frames = ajoMexAnimated(), halframes = ajoMexSadAnimated(), emptyFrames = ajoMexVerySadAnimated(),desbloqueado = false),
        Sombrero(nombre = "Lentes", precio = 45, imagen = hatImages[2],descripcion = "Un ajolote misterioso las dejó diciendo: 'Con estas, nadie sabrá si estás durmiendo o planeando algo increíble.'",frames = ajoLentesAnimated(), halframes = ajoLentesSadAnimated(), emptyFrames = ajoLentesVerySadAnimated(),desbloqueado = false),
        Sombrero(nombre = "Vikingo", precio = 100, imagen = hatImages[1],descripcion = "Un relicario de los ajolotes exploradores del norte. ¡Prepárate para conquistar cualquier pecera!",frames = ajoVikingoAnimated(), halframes = ajoVikingoSadAnimated(), emptyFrames = ajoVikingoVerySadAnimated(),desbloqueado = false),
    )
    return imagenes
}

@Composable
fun achivements(achivemntsImages: List<Painter> = achivemntsImages()): List<Achievements> {
    val imagenes = listOf(
        Achievements(nombre = "ducha rapida",id = 0, imagen =achivemntsImages[0], descripcion = "Toma 30 duchas rapidas", goal = 30, progress = 0, completed = false),
        Achievements(nombre = "super ahorrador",id = 1, imagen =achivemntsImages[1], descripcion = "Ahorra más de 10,000 litros de agua", goal = 10000, progress = 0, completed = false),
        Achievements(nombre = "ahorro semanal",id = 2, imagen =achivemntsImages[2], descripcion = "Compra todos los objetos de la tienda", goal = 8, progress = 0, completed = false)
    )
    return imagenes
}

@Composable
fun tutorials(): List<Tutorial> {
    val imagenes = listOf(
        Tutorial(firstText = "¡Bienvenido a EcoSplash!\n" +
                "¡Una app donde cuidas el agua, divirtiéndote!\n " +
                "Para comenzar, conoce las características y botones principales\n", imageSize = 0.25f, textSize = 0.025f, top = 0.0f, bottom = 0.0f,image = painterResource(id = R.drawable.sprite_00), secondText = "Este es tu ajolote, cuidalo muy bien, conforme progreses irá creciendo y podrás personalizarlo"),
        Tutorial(firstText = "¡Bienvenido a EcoSplash!\n" +
                "¡Una app donde cuidas el agua, divirtiéndote!\n " +
                "Para comenzar, conoce las características y botones principales\n", imageSize = 0.12f, textSize = 0.0f,top = 0.0f, bottom = 0.0f, image = painterResource(id = R.drawable.clock), secondText = "Botón de temporizador: Justo cuando estés a punto de meterte a bañar, presiona el botón de temporizador para tomar el tiempo. Cuando termines, oprimelo una vez más para detenerlo. ¡Si tu ducha es menor a 8 minutos, ganarás más recompensas!"),
        Tutorial(firstText = "¡Bienvenido a EcoSplash!\n" +
                "¡Una app donde cuidas el agua, divirtiéndote!\n " +
                "Para comenzar, conoce las características y botones principales\n", imageSize = 0.12f, textSize = 0.025f,top = 0.04f, bottom = 0.04f,image = painterResource(id = R.drawable.digitalclock), secondText = "Temporizador: Aquí se mostrará el tiempo restante de la ducha para conseguir más recompensas. ¡No dejes que el reloj llegue a 0!"),
        Tutorial(firstText = "¡Bienvenido a EcoSplash!\n" +
                "¡Una app donde cuidas el agua, divirtiéndote!\n " +
                "Para comenzar, conoce las características y botones principales\n",imageSize = 0.08f, textSize = 0.025f,top = 0.03f, bottom = 0.02f,image = painterResource(id = R.drawable.rachav2), secondText = "Racha: Cada vez que completes una ducha en menos de 8 minutos, esta irá incrementando, entre mayor sea la racha, mayores serán las recompensas. ¡No la pierdas!"),
        Tutorial(firstText = "¡Bienvenido a EcoSplash!\n" +
                "¡Una app donde cuidas el agua, divirtiéndote!\n " +
                "Para comenzar, conoce las características y botones principales\n", imageSize = 0.08f, textSize = 0.025f,top = 0.03f, bottom = 0.02f,image = painterResource(id = R.drawable.dinero), secondText = "¡Monedas!: Ganarás monedas conforme tomes duchas rápidas, entre menor sea el tiempo de tu ducha, ganarás más monedas, las cuales podrás gastar en la tienda"),
        Tutorial(firstText = "¡Bienvenido a EcoSplash!\n" +
                "¡Una app donde cuidas el agua, divirtiéndote!\n " +
                "Para comenzar, conoce las características y botones principales\n", imageSize = 0.09f, textSize = 0.025f,top = 0.06f, bottom = 0.06f,image = painterResource(id = R.drawable.edit_button), secondText = "Tienda: Accede a la tienda por medio de este botón, en ella podrás gastar tus monedas para adquirir accesorios para tu ajolote y pecera"),
        Tutorial(firstText = "¡Bienvenido a EcoSplash!\n" +
                "¡Una app donde cuidas el agua, divirtiéndote!\n " +
                "Para comenzar, conoce las características y botones principales\n", imageSize = 0.09f, textSize = 0.025f,top = 0.06f, bottom = 0.06f,image = painterResource(id = R.drawable.activity), secondText = "Estadísticas: Oprime el botón de estadísticas para ver tus duchas totales, duchas rápidas y litros de agua ahorrados."),
        Tutorial(firstText = "¡Bienvenido a EcoSplash!\n" +
                "¡Una app donde cuidas el agua, divirtiéndote!\n " +
                "Para comenzar, conoce las características y botones principales\n", imageSize = 0.09f, textSize = 0.025f,top = 0.05f, bottom = 0.05f,image = painterResource(id = R.drawable.award), secondText = "Botón de logros: Ganarás medallas conforme alcances los logros, puedes ver tu progreso y metas cumplidas. ¡Completa todos!"),

        )
    return imagenes
}