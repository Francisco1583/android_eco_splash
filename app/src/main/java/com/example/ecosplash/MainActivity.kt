package com.example.ecosplash
import android.graphics.Paint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import com.example.ecosplash.classes.Sombrero
//import com.example.ecosplash.menus.BackgroupsMenu
import com.example.ecosplash.menus.EditMenu
import com.example.ecosplash.menus.HatMenu
//import com.example.ecosplash.menus.HatMenu
import com.example.ecosplash.menus.MainMenu
//import com.example.ecosplash.popups.ItemDetails
import com.example.ecosplash.popups.MoreInfo
import com.example.ecosplash.popups.Stats
import com.example.ecosplash.topInterfaces.Firstopart
import com.example.ecosplash.topInterfaces.Secondtopart
import com.example.ecosplash.ui.theme.EcosplashTheme
import kotlinx.coroutines.delay
import java.util.Locale
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.TimeUnit

val montserratFontFamily = FontFamily(
    Font(R.font.montserrat, FontWeight.Normal)
)
val tekoFontFamily = FontFamily(
    Font(R.font.teko, FontWeight.Normal)
)

class MainActivity1 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EcosplashTheme {
                Surface {
                    Greeting1(imagenes = images(), fishbowlanimation = fisbowlanimated(), ajoAnimated = ajoAnimated(), hatOptions = hatOptions(), backgrounds = backgrounds())
                }
            }
        }
    }
}


@Composable
fun formatTimer(timeMi: Long): String {
    val min = TimeUnit.MILLISECONDS.toMinutes(timeMi) % 60
    val sec = TimeUnit.MILLISECONDS.toSeconds(timeMi) % 60

    return String.format(Locale.getDefault(),"%02d:%02d",min,sec)
}

@Composable
fun ItemDetails(onDismiss:()-> Unit,
                imagenes: List<Painter>,
                maxHeight: Dp, accesorie: Sombrero,
                money: Int,
                setMoney: (Int) -> Unit
                ) {
    var noMoney by remember { mutableStateOf(false)}
    androidx.compose.material3.AlertDialog(
        onDismissRequest =  onDismiss,
        confirmButton = { /*TODO*/ },
        text = {
            Column {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    IconButton(onClick = onDismiss,
                        modifier = Modifier
                            .height((maxHeight * 0.07f))
                    ) {
                        Image(
                            painter = imagenes[13],
                            contentDescription = "icono de X",
                            contentScale = ContentScale.FillWidth,
                            modifier = Modifier
                                .fillMaxSize()
                        )
                    }
                }

                Image(painter = accesorie.imagen,
                    contentDescription = "icono del accesorio",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .height(maxHeight * 0.1f)
                        .align(Alignment.CenterHorizontally)

                )
                Text(
                    text = accesorie.descripcion,
                    modifier = Modifier
                        .padding(top = maxHeight*0.01f),
                    textAlign = TextAlign.Center,
                    fontFamily = montserratFontFamily,
                    color = Color.Black
                )
                if(noMoney) {
                    Text(
                        text = "DINERO INSUFICIENTE",
                        modifier = Modifier
                            .padding(top = maxHeight*0.01f)
                            .align(Alignment.CenterHorizontally),
                        textAlign = TextAlign.Center,
                        fontFamily = montserratFontFamily,
                        color = Color.Red
                    )
                }


                Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                    .fillMaxSize()
                    .padding(top = maxHeight * 0.01f)) {
                    if(!accesorie.desbloqueado) {
                        Button(onClick = { if(money >= accesorie.precio) {
                            accesorie.desbloqueado = true
                            setMoney(money-accesorie.precio)
                            onDismiss()

                        }

                        else {
                            noMoney = true
                        }}, modifier = Modifier.weight(1f)) {
                            Text(text = "comprar",
                                fontFamily = montserratFontFamily,
                                color = Color.Black)
                        }
                    }
                    else {
                        Text(text = "DESBLOQUEADO",
                            fontFamily = montserratFontFamily,
                            color = Color.Blue,
                            modifier = Modifier
                                .padding(top = maxHeight*0.02f))
                    }

                }
            }

        },
        containerColor = Color(0xFFCBE2FE),
        modifier = Modifier
            .height(maxHeight * 0.5f)
            .padding(8.dp)
    )
}

@Composable
// editmenu es el menú que se despliega al darle al botón de editar
fun BackgroupsMenu(onClick: (Int) -> Unit,
                   bgColor: Color = Color.Red,
                   imagenes: List<Painter>,
                   maxHeight: Dp,
                   backgrounds: List<Sombrero>,
                   fishbowlacc: Int,
                   setFishBowlAcc: (Int) -> Unit,
                   modifier: Modifier = Modifier,
                   money: Int,
                   setMoney: (Int) -> Unit) {
    var clicks by remember { mutableIntStateOf(0) }
    var showDialog by remember { mutableStateOf(false)}
    val setInfoDialog: (Boolean) -> Unit = {newinfoDialog -> showDialog = newinfoDialog}
    var prevfishbowlass by remember { mutableIntStateOf(0) }
    var position by remember { mutableIntStateOf(fishbowlacc)}
    Column() {
        IconButton(onClick =
        {onClick(2)
            if (backgrounds[fishbowlacc].desbloqueado) {
                setFishBowlAcc(position)
            }
            else if (backgrounds[prevfishbowlass].desbloqueado) {
                setFishBowlAcc(prevfishbowlass)
            }
            else {
                setFishBowlAcc(0)
            }
                             },
            modifier = Modifier
            //.height((maxHeight * 0.08f))
            // .align(Alignment.Top)
        )
        {
            Image(painter = imagenes[12],
                contentDescription = "icono de flecha hacia atrás",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxSize()
                    //.align(Alignment.TopStart)
                    .padding(maxHeight * 0.009f)
            )
        }
        var selectedIndex by remember { mutableIntStateOf(fishbowlacc) }
        LazyRow(//horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxHeight()) {
            item { Spacer(modifier = Modifier.padding(10.dp)) }
            items(backgrounds.size) { pos ->
                // Spacer(modifier = Modifier.padding(10.dp))
                IconButton(onClick = {
                    if (selectedIndex != pos) {
                        clicks = 0
                        clicks += 1
                    }
                    else {
                        clicks += 1
                    }

                    if (clicks == 2) {showDialog = true}
                    selectedIndex = pos
                    position = pos
                    prevfishbowlass = fishbowlacc
                    setFishBowlAcc(pos)
                                     },
                    modifier = Modifier
                        .height((maxHeight * 0.16f))
                        .width(maxHeight * 0.16f)
                        .background(Color(0xFFCBE2FE), shape = RoundedCornerShape(20.dp))
                        .border(
                            width = if (selectedIndex == pos) 4.dp else 0.dp,
                            color = if (selectedIndex == pos) Color.Green else Color.Transparent,
                            shape = RoundedCornerShape(20.dp)
                        )

                )
                {

                    Image(painter = backgrounds[pos].imagen,
                        contentDescription = "icono sombrero",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(maxHeight * 0.03f)

                    )
                    Row(modifier = Modifier.offset(y = maxHeight*0.065f)) {
                        Text(
                            text = if (!backgrounds[pos].desbloqueado) "$" + backgrounds[pos].precio else "lo tienes",
                            fontFamily = montserratFontFamily,
                            color = Color.Black
                        )
                    }

                }
                Spacer(modifier.padding(maxHeight*0.012f))
            }

        }
    }
        if (showDialog){
            clicks = 0
            ItemDetails(onDismiss = {showDialog = false},
                imagenes = imagenes,
                maxHeight = maxHeight,
                accesorie = backgrounds[position],
                money = money,
                setMoney = setMoney)
        }


}



@Composable
fun Greeting1(imagenes: List<Painter>,
              fishbowlanimation: List<Painter>,
              ajoAnimated: List<Painter>,
              hatOptions: List<Painter>,
              backgrounds: List<Sombrero>,
              hats: List<Sombrero> = hats()) {

    // -------------DECLARACIÓN DE VARIABLES --------------------------
    // variable que se usa para el cambio de menus
    var boxVisible by remember { mutableIntStateOf(1) }
    // variable para mostrar o no el popup
    var showDialog by remember { mutableStateOf(false)}
    // mostrar dialogo de logros
    var showAchivments by remember { mutableStateOf(false)}
    // variable para mostrar o no el popup1
    var showDialogStats by remember { mutableStateOf(false)}
    // variable que define el progreso de la barra de nivel
    var progress by remember { mutableFloatStateOf(0.0f) }
    // nivel actual
    var level by remember {mutableIntStateOf(0)}
    // experiencia
    var experience by remember { mutableIntStateOf(0)}
    // experiencia total para el siguiente nivel
    var totalExperience by remember { mutableIntStateOf(400)}
    //variable que almacena la cantidad de monedas
    var money by remember {mutableIntStateOf(0) }
    // variable que almacena la cantidad de las rachas
    var racha by remember {mutableIntStateOf(0)}
    //variable que almacena el tiempo del temporizador
    var time by remember { mutableLongStateOf(1200000L) }
    //variable que sirve para identificar si el temporizadore está corriendo o no
    var isRunning by remember { mutableStateOf(false) }
    //variable para animación de pecera
    var isRunningFishbowl by remember { mutableStateOf(true)}
    var isRunningAjoAnimated by remember { mutableStateOf(true) }
    // variable que cambia de acuerdo al frame de la animación de la pecera
    var fishbowlIndex by remember { mutableIntStateOf(0) }
    //variable que cambia de acuerdo al frame de la animación del ajolote
    var ajoIndex by remember { mutableIntStateOf(0) }
    // variable que probablemente la vaya a eliminar
    var startTime by remember { mutableLongStateOf(0L) }
    // variable que almacenará las duchas totales
    var duchasTotales by remember { mutableIntStateOf(0) }
    var duchasMen5 by remember { mutableIntStateOf(0) }
    var litrosAhorrados by remember { mutableFloatStateOf(0.0f) }
    //variable que indica que accesorio de la pecera está seleccionado
    var fishbowlacc by remember { mutableIntStateOf(0) }
    var hatAcc by remember { mutableIntStateOf(0) }


    // -----------------DECLARACIÓN DE VALORES PARA MODIFICAR LAS VARIABLES --------------
    // con esta variable es posible cambiar el valor de boxvisible
    val onClick = { newState : Int -> boxVisible = newState }
    // con esta variable es posible cambiar el valor de time
    val setTime: (Long) -> Unit = {newTime -> time = newTime}
    // con este valor es posible modificar isRunning
    val setIsRunning: (Boolean) -> Unit = {running -> isRunning = running}
    // se modifica starTime, probablemente ya no se use
    val setStartTime: (Long) -> Unit = {newStartTime -> startTime = newStartTime}
    // valor para cambiar la variable booleana con la que se decide si mostrar o no el popup de información
    val setInfoDialog: (Boolean) -> Unit = {newinfoDialog -> showDialog = newinfoDialog}
    //valor para modificar el booleano del popup de las estadisticas
    val setStatsDialog: (Boolean) -> Unit = {newinfoDialog -> showDialogStats = newinfoDialog}
    //para modificar el booleano del popup de las logros
    val setAchivmentDialig: (Boolean) -> Unit = {newAchivmentD -> showAchivments = newAchivmentD}
    //valor para modificar lo del dinero
    val setMoney: (Int) -> Unit = {moreMoney -> money = moreMoney}
    // valor para modificar lo de la racha
    val setRacha: (Int) -> Unit = {masRacha -> racha = masRacha}
    // modifica el progreso de la barra
    val setProgress: (Float) -> Unit = {newProgress -> progress = newProgress}
    // actualiza la variable de las duchas totales
    val setduchasTotales: (Int) -> Unit = {masduchasT -> duchasTotales = masduchasT}
    // actializa la variable de las duchas menores a 5 minutos
    val setduchasMen5: (Int) -> Unit = {masduchasMen5 -> duchasMen5 = masduchasMen5}
    // actualiza la cantidad de litros ahorrados
    val setLitrosAhorrados: (Float) -> Unit = {masLitrosAhorrados -> litrosAhorrados = masLitrosAhorrados}
    // valor para cambiar fishbowlacc
    val setFishBowlAcc: (Int) -> Unit = {newFishBowlAcc -> fishbowlacc = newFishBowlAcc}
    val setHatAcc: (Int) -> Unit = {newHat -> hatAcc = newHat}
    // valor para cambiar la variable de experiencia
    val setExperience: (Int) -> Unit = {newExperience -> experience = newExperience}
    // valor para cambiar el nivel
    val setLevel: (Int) -> Unit = {newLevel -> level = newLevel}
    // valor para cambiar la experiencia necesaria para el siguiente nivel
    val setTotalExperience: (Int) -> Unit = {newTotal -> totalExperience = newTotal}
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFD9F1FF))
    ) {
        val maxWidth = maxWidth
        val maxHeight = maxHeight

        Image(painter = imagenes[0],
            contentDescription = "imagen del cuarto",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = maxHeight * 0.25f)

        )
        Firstopart(maxWidth = maxWidth,
            maxHeight = maxHeight,
            imagenes = imagenes,
            setInfoDialog = setInfoDialog,
            setAchivmentDialig = setAchivmentDialig
            )
        Secondtopart(progress = progress,
            maxWidth = maxWidth,
            maxHeight = maxHeight,
            imagenes = imagenes,
            money = money,
            racha = racha,
            level = level)
        //Image(painter = fishbowlanimation[fishbowlIndex],
        Image(painter = backgrounds[fishbowlacc].frames[fishbowlIndex],
            contentDescription = "imagen de la pecera",
            contentScale = ContentScale.Crop,
            modifier = Modifier

                .height(maxHeight * 0.4f)
                .align(Alignment.Center)
                .offset(y = maxHeight * 0.07f)
        )
        Image(painter = hats[hatAcc].frames[ajoIndex],
            contentDescription = "imagen del ajolote",
            contentScale = ContentScale.Fit,
            modifier = Modifier

                .fillMaxSize()
                .align(Alignment.Center)
                .offset(y = maxHeight * 0.1f)
                .offset(x = maxWidth * -0.1f)
                .padding(maxHeight * 0.12f)

        )
        if (isRunning) {
            Image(painter = imagenes[14],
                contentDescription = "imagen del cuarto",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = maxHeight * 0.78f)
                    .height(maxHeight * 0.1f)

            )
            Text(text = formatTimer(timeMi = time), style = TextStyle(fontSize = 40.sp), fontFamily =tekoFontFamily, modifier = Modifier
                .padding(9.dp)
                .align(Alignment.Center)
                .offset(y = maxHeight * 0.33f),
                color = Color.White)
        }


        if (boxVisible == 2 && !isRunning) {
            Surface(color = Color(0xFFFFFFFF),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomEnd)
                    //.height(maxHeight * 0.13f)
                    .height(maxHeight * 0.20f)
            ) {
                EditMenu(imagenes = imagenes, maxHeight = maxHeight, onClick = onClick)
            }
        }
        else if(boxVisible == 1){
            Surface(color = Color(0xFFFFFFFF),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomEnd)
                    .height(maxHeight * 0.13f)
            ) {
                MainMenu(imagenes = imagenes,
                    maxHeight = maxHeight,
                    onClick = onClick, time = setTime,
                    isRunning = setIsRunning,
                    isCurrentlyRunning = isRunning,
                    currentTime = time,
                    setMoney = setMoney,
                    money = money,
                    setRacha = setRacha,
                    racha = racha,
                    progress = progress,
                    setProgress = setProgress,
                    setStatsDialog = setStatsDialog,
                    showDialogStats = showDialogStats,
                    setduchasTotales = setduchasTotales,
                    setLitrosAhorrados = setLitrosAhorrados,
                    setduchasMen5 = setduchasMen5,
                    duchasTotales = duchasTotales,
                    duchasMen5 = duchasMen5,
                    litrosAhorrados = litrosAhorrados,
                    level = level,
                    setLevel = setLevel,
                    experience = experience,
                    setExperience = setExperience,
                    totalExperience = totalExperience,
                    setTotalExperience = setTotalExperience)

            }
        }
        else if(boxVisible == 3) {
            Surface(color = Color(0xFFFFFFFF),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomEnd)
                    //.height(maxHeight * 0.13f)
                    .height(maxHeight * 0.24f)
            ) {
                BackgroupsMenu(imagenes = imagenes,
                    maxHeight = maxHeight,
                    onClick = onClick,
                    backgrounds = backgrounds,
                    fishbowlacc = fishbowlacc,
                    setFishBowlAcc = setFishBowlAcc,
                    money = money,
                    setMoney = setMoney
                )
            }
        }
        else if(boxVisible == 4) {
            Surface(color = Color(0xFFFFFFFF),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomEnd)
                    //.height(maxHeight * 0.13f)
                    .height(maxHeight * 0.24f)
            ) {
                BackgroupsMenu(imagenes = imagenes,
                    maxHeight = maxHeight,
                    onClick = onClick,
                    backgrounds = hats,
                    fishbowlacc = hatAcc,
                    setFishBowlAcc = setHatAcc,
                    money = money,
                    setMoney = setMoney
                )
//                HatMenu(imagenes = imagenes,
//                    maxHeight = maxHeight,
//                    onClick = onClick,
//                    hatOptions = hatOptions
//                )
            }
        }
        else {
            boxVisible = 1
        }
        if (showDialog){
            MoreInfo(onDismiss = {showDialog = false},imagenes = imagenes, maxHeight = maxHeight)
        }
        if (showDialogStats) {
            Stats(onDismiss = {showDialogStats = false},
                imagenes = imagenes,
                maxHeight = maxHeight,
                duchasTotales = duchasTotales,
                duchasMen5 = duchasMen5,
                litrosAhorrados = litrosAhorrados)
        }
        if (showAchivments) {
            MoreInfo(onDismiss = {showAchivments = false},
                imagenes = imagenes,
                maxHeight = maxHeight)
        }
    }
    LaunchedEffect(isRunning) {
        while (isRunning) {
            delay(1000)
            time -= 1000
            if (time.toInt() == 0) {
                isRunning = false
                time = 600000
            }
        }
    }
    LaunchedEffect(isRunningFishbowl) {
        while (isRunningFishbowl) {
            delay(85)
            fishbowlIndex = (fishbowlIndex + 1) % 44
        }
    }

    LaunchedEffect(isRunningAjoAnimated) {
        while (isRunningAjoAnimated) {
            delay(100)
            ajoIndex = (ajoIndex + 1) % 14
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview1() {
    EcosplashTheme {
        Greeting1(imagenes = images(),
            fishbowlanimation = fisbowlanimated(),
            ajoAnimated = ajoAnimated(),
            hatOptions = hatOptions(),
            backgrounds = backgrounds()
        )
    }
}